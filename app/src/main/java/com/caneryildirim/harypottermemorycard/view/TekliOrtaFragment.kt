package com.caneryildirim.harypottermemorycard.view

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.caneryildirim.harypottermemorycard.R
import com.caneryildirim.harypottermemorycard.databinding.FragmentTekliOrtaBinding
import com.caneryildirim.harypottermemorycard.util.Kart
import com.caneryildirim.harypottermemorycard.util.MemoryCard
import com.caneryildirim.harypottermemorycard.util.Singleton.kartList




class TekliOrtaFragment : Fragment() {
    private var _binding:FragmentTekliOrtaBinding?=null
    private val binding get() = _binding!!

    private lateinit var sayici: CountDownTimer          //Sayaç
    private var mediaPlayer: MediaPlayer?=null          //Müzik oynatıcı
    private lateinit var buttons:List<ImageView>        //imageView listesi
    private var kartlar=ArrayList<Kart>()               //imageView kartlara dağılan liste
    private lateinit var cards:List<MemoryCard>         //kartların kontrol listesi
    private var indexOfSingleSelectedCard:Int?=null     //kart açıcı kontrol değişkeni
    private var countMatch:Int=0                        //kaç eşmeşme olduğunu sayar
    private var kalanSure:Long=45                       //kalan süre değişkeni
    private var puan:Double=0.0                         //puan değişkeni

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentTekliOrtaBinding.inflate(inflater,container,false)
        val view=binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        //fragment destroy edilirken sayici ve media player kapanır
        _binding=null
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer=null
        sayici.cancel()
    }

    override fun onResume() {
        super.onResume()
        //fragment çalışmaya devam ederken ana müzik tekrarlı çalacak
        mediaPlayer=MediaPlayer.create(this.requireContext(), R.raw.anamuzik)
        mediaPlayer?.start()
        mediaPlayer?.isLooping=true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        muzikCalar()        //müziğin sesini kapatıp açma fonksiyonu
        kartDagitim()       //firebase den gelen listeyi imageView lara dağıtma fonksiyonu
        buttonInit()        //imageView listesinin fonksiyonu
        geriSayim()         //geri sayımı çalıştıran fonksiyon


        //imageVİew a yüklenen kartlar ile aynı sirada cards listesi oluşturulur
        cards=buttons.indices.map { index->
            MemoryCard(kartlar[index])
        }

        val notDefteri=ArrayList<String>()      //not defterinde verileri göstermek için liste

        buttons.forEachIndexed { index, imageView ->
            Log.d("NotDefteri","imageView${index+1}=${kartlar[index].kartIsim}")    //kartların logcat takibi
            notDefteri.add("imageView${index+1}=${kartlar[index].kartIsim}")
            imageView.setOnClickListener {
                updateModels(index)     //en fazla 2 kart açılmasını sağlayan  fonksiyon
                updateViews()           //kartın ön yüzünü ya da arka yüzünü açan fonksiyon
            }
        }


    }

    private fun geriSayim() {
        sayici=object :CountDownTimer(45000,1000){
            override fun onTick(p0: Long) {
                binding.textViewSureTO.text="Time: ${p0/1000}"
                kalanSure=p0/1000
            }

            override fun onFinish() {
                mesajSureBitti()
                sureBittiMuzik()
                buttons.forEach {imageView ->
                    imageView.isEnabled=false
                }
            }

        }.start()
    }

    private fun finish() {
        mesajKazandin()
        zaferMuzik()
        sayici.cancel()
        buttons.forEach {imageView ->
            imageView.isEnabled=false
        }
    }

    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val imageView=buttons[index]
            if (card.isMatched){
                imageView.alpha=0.1f
            }
            if (card.isFaceUp){
                imageView.setImageResource(card.identifier.image)
            }else{
                imageView.setImageDrawable(requireContext().getDrawable(R.drawable.kartarka))
            }
        }
    }

    private fun updateModels(index:Int) {
        val card=cards[index]
        if (card.isFaceUp){
            Toast.makeText(requireContext(),"Invalid move!",Toast.LENGTH_SHORT).show()
            return
        }
        if (indexOfSingleSelectedCard==null){
            restoreCards()                          // kartlar eşleştiyse bir daha yüzlerini çevirme
            indexOfSingleSelectedCard=index
        }else{
            checkForMatch(indexOfSingleSelectedCard!!,index)       //eşleşme var mı kontrol et
            indexOfSingleSelectedCard=null
        }


        card.isFaceUp=!card.isFaceUp
    }

    private fun restoreCards() {
        for (card in cards){
            if (!card.isMatched){
                card.isFaceUp=false
            }
        }
    }

    private fun checkForMatch(position1:Int,position2: Int) {
        if (cards[position1].identifier.kartIsim==cards[position2].identifier.kartIsim){
            // kartlar eşleşti
            cards[position1].isMatched=true
            cards[position2].isMatched=true
            countMatch=countMatch+1

            //ekrana kart bilgilerini anlık getir
            val kartinAdi=cards[position1].identifier.kartIsim
            val kartinPuani=cards[position1].identifier.kartPuan
            val kartinEvi=cards[position1].identifier.ev
            Toast.makeText(requireContext(),"${kartinAdi},(Score:${kartinPuani},Home:${kartinEvi})",Toast.LENGTH_SHORT).show()

            //puanEkle
            val kartPuan=cards[position1].identifier.kartPuan
            val evPuan=cards[position1].identifier.evPuan
            puan=puan+(2*kartPuan*evPuan)*(kalanSure/10)
            binding.textPuan1.text="Score: ${puan.toInt()}"

            //eşleşme müziği çal
            if (countMatch==8){
                finish()           //8  eşleşmede oyun biter
            }else if (countMatch>0 && countMatch<8){
                eslesmeMuzik()      //8 eşleşmeden küçükse hepsinde esleşme müziği çal
            }

        }else{
            //kartlar eşleşmedi
            //puanÇıkar
            val kart1Puan=cards[position1].identifier.kartPuan
            val kart2Puan=cards[position2].identifier.kartPuan
            val ev1Puan=cards[position1].identifier.evPuan
            val ev2Puan=cards[position2].identifier.evPuan
            val kart1Ev=cards[position1].identifier.ev
            val kart2Ev=cards[position2].identifier.ev
            val gecenSure=45-kalanSure


            if (kart1Ev==kart2Ev){
                puan=puan-((kart1Puan+kart2Puan)/ev1Puan)*(gecenSure/10)
                binding.textPuan1.text="Score: ${puan.toInt()}"
            }else{
                puan=puan-((kart1Puan+kart2Puan)/2*ev1Puan*ev2Puan)*(gecenSure/10)
                binding.textPuan1.text="Score: ${puan.toInt()}"
            }

        }
    }

    private fun buttonInit() {
        buttons= listOf(binding.imageKart1TO,binding.imageKart2TO,binding.imageKart3TO,binding.imageKart4KO,binding.imageKart5TO,
            binding.imageKart6TO,binding.imageKart7TO,binding.imageKart8TO,binding.imageKart9TO,binding.imageKart10TO,
            binding.imageKart11TO,binding.imageKart12TO,binding.imageKart13TO,binding.imageKart14TO,binding.imageKart15TO,
            binding.imageKart16TO)
    }

    private fun muzikCalar() {
        binding.music.setOnClickListener {
            if (mediaPlayer!!.isPlaying){
                mediaPlayer?.pause()
                binding.music.setImageDrawable(requireContext().getDrawable(R.drawable.musicoff))
            }else{
                mediaPlayer=MediaPlayer.create(this.requireContext(), R.raw.anamuzik)
                mediaPlayer?.start()
                mediaPlayer?.isLooping=true
                binding.music.setImageDrawable(requireContext().getDrawable(R.drawable.musicon))
            }
        }

    }

    private fun kartDagitim() {
        kartList.shuffle()
        kartlar.clear()

        val kartListGryfinndor=kartList.filter { it.ev=="GRYFFİNDOR" }
        val kartListSlytherin= kartList.filter { it.ev=="SLYTHERİN" }
        val kartListRavenclaw= kartList.filter { it.ev=="RAVENCLAW" }
        val kartlistHufflepuff= kartList.filter { it.ev=="HUFFLEPUFF" }
        kartListGryfinndor.shuffled()
        kartListSlytherin.shuffled()
        kartListRavenclaw.shuffled()
        kartlistHufflepuff.shuffled()

        for (x in 0..1){
            kartlar.add(kartListGryfinndor[x])
        }

        for (x in 0..1){
            kartlar.add(kartListSlytherin[x])
        }

        for (x in 0..1){
            kartlar.add(kartListRavenclaw[x])
        }

        for (x in 0..1){
            kartlar.add(kartlistHufflepuff[x])
        }

        kartlar.addAll(kartlar)
        kartlar.shuffle()
    }

    private fun sureBittiMuzik() {
        if (mediaPlayer!!.isPlaying){
            mediaPlayer!!.stop()
            mediaPlayer=null
            mediaPlayer=MediaPlayer.create(requireContext(),R.raw.surebitti)
            mediaPlayer?.start()
        }
    }

    private fun eslesmeMuzik() {

    }

    private fun zaferMuzik() {
        if (mediaPlayer!!.isPlaying){
            mediaPlayer!!.stop()
            mediaPlayer=null
            mediaPlayer=MediaPlayer.create(requireContext(),R.raw.kazandin)
            mediaPlayer?.start()
        }
    }

    private fun mesajKazandin(){
        val builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle("You won")
        builder.setMessage("Your score: ${puan.toInt()}")
        builder.show()
    }

    private fun mesajSureBitti(){
        val builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle("The time is over")
        builder.setMessage("Your score: ${puan.toInt()}")
        builder.show()
    }


}