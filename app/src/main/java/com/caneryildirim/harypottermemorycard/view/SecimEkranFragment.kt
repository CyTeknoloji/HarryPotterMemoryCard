package com.caneryildirim.harypottermemorycard.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.caneryildirim.harypottermemorycard.R
import com.caneryildirim.harypottermemorycard.databinding.FragmentSecimEkranBinding
import com.caneryildirim.harypottermemorycard.util.Kart
import com.caneryildirim.harypottermemorycard.util.Singleton.kartList


class SecimEkranFragment : Fragment() {
    private var _binding: FragmentSecimEkranBinding? = null
    private val binding get() = _binding!!
    private var oyuncuModu: String? = null
    private var oyuncuSeviye: String? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecimEkranBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        veriAl()

        //butonların işlevleri
        binding.buttonTekOyuncu.setOnClickListener {
            it.setBackgroundColor(requireActivity().getColor(R.color.green))
            binding.buttonCokluOyuncu.setBackgroundColor(requireActivity().getColor(R.color.purple_200))
            oyuncuModu = "TekOyuncu"
        }

        binding.buttonCokluOyuncu.setOnClickListener {
            it.setBackgroundColor(requireActivity().getColor(R.color.green))
            binding.buttonTekOyuncu.setBackgroundColor(requireActivity().getColor(R.color.purple_200))
            oyuncuModu = "ÇokluOyuncu"
        }

        binding.buttonKolay.setOnClickListener {
            it.setBackgroundColor(requireActivity().getColor(R.color.green))
            binding.buttonOrta.setBackgroundColor(requireActivity().getColor(R.color.purple_200))
            binding.buttonZor.setBackgroundColor(requireActivity().getColor(R.color.purple_200))
            oyuncuSeviye = "kolay"
        }

        binding.buttonOrta.setOnClickListener {
            it.setBackgroundColor(requireActivity().getColor(R.color.green))
            binding.buttonKolay.setBackgroundColor(requireActivity().getColor(R.color.purple_200))
            binding.buttonZor.setBackgroundColor(requireActivity().getColor(R.color.purple_200))
            oyuncuSeviye = "orta"
        }

        binding.buttonZor.setOnClickListener {
            it.setBackgroundColor(requireActivity().getColor(R.color.green))
            binding.buttonKolay.setBackgroundColor(requireActivity().getColor(R.color.purple_200))
            binding.buttonOrta.setBackgroundColor(requireActivity().getColor(R.color.purple_200))
            oyuncuSeviye = "zor"
        }

        binding.buttonBasla.setOnClickListener {
            //oyuncu modu ve seviyesi seçildi ise oyuna başlatır
            if (oyuncuModu != null && oyuncuSeviye != null) {
                if (oyuncuModu == "TekOyuncu") {
                    when (oyuncuSeviye) {
                        "kolay" -> {
                            val action =
                                SecimEkranFragmentDirections.actionSecimEkranFragmentToTekliKolayFragment()
                            Navigation.findNavController(it).navigate(action)
                        }
                        "orta" -> {
                            val action =
                                SecimEkranFragmentDirections.actionSecimEkranFragmentToTekliOrtaFragment()
                            Navigation.findNavController(it).navigate(action)
                        }
                        "zor" -> {
                            val action =
                                SecimEkranFragmentDirections.actionSecimEkranFragmentToTekliZorFragment()
                            Navigation.findNavController(it).navigate(action)
                        }
                    }
                } else if (oyuncuModu == "ÇokluOyuncu") {
                    when (oyuncuSeviye) {
                        "kolay" -> {
                            val action =
                                SecimEkranFragmentDirections.actionSecimEkranFragmentToCokluKolayFragment()
                            Navigation.findNavController(it).navigate(action)
                        }
                        "orta" -> {
                            val action =
                                SecimEkranFragmentDirections.actionSecimEkranFragmentToCokluOrtaFragment()
                            Navigation.findNavController(it).navigate(action)
                        }
                        "zor" -> {
                            val action =
                                SecimEkranFragmentDirections.actionSecimEkranFragmentToCokluZorFragment()
                            Navigation.findNavController(it).navigate(action)
                        }
                    }
                }
            } else {
                //oyun modu ve seviyeden birisi seçilmediği zaman
                Toast.makeText(
                    this.requireContext(),
                    "Oyun modunu ve seviyesini seçin!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }


    private fun veriAl() {
        kartList.clear()

        val image1 = R.drawable.peterpettigrew
        val ev1 = "GRYFFİNDOR"
        val evPuan1 = "2"
        val kartIsim1 = "Peter Pettigrew"
        val kartPuan1 = "5"
        val kart1 = Kart(ev1, evPuan1.toInt(), kartIsim1, kartPuan1.toInt(), image1)
        kartList.add(kart1)

        val image2 = R.drawable.gilderoylockhart
        val ev2 = "RAVENCLAW"
        val evPuan2 = "1"
        val kartIsim2 = "Gilderoy Lockhart"
        val kartPuan2 = "13"
        val kart2 = Kart(ev2, evPuan2.toInt(), kartIsim2, kartPuan2.toInt(), image2)
        kartList.add(kart2)

        val image3 = R.drawable.narcissamalfoy
        val ev3 = "SLYTHERİN"
        val evPuan3 = "2"
        val kartIsim3 = "Narcissa Malfoy"
        val kartPuan3 = "10"
        val kart3 = Kart(ev3, evPuan3.toInt(), kartIsim3, kartPuan3.toInt(), image3)
        kartList.add(kart3)

        val image4 = R.drawable.lunalovegood
        val ev4 = "RAVENCLAW"
        val evPuan4 = "1"
        val kartIsim4 = "Luna Lovegood"
        val kartPuan4 = "9"
        val kart4 = Kart(ev4, evPuan4.toInt(), kartIsim4, kartPuan4.toInt(), image4)
        kartList.add(kart4)

        val image5 = R.drawable.garrickollivander
        val ev5 = "RAVENCLAW"
        val evPuan5 = "1"
        val kartIsim5 = "Garrick Ollivander"
        val kartPuan5 = "15"
        val kart5 = Kart(ev5, evPuan5.toInt(), kartIsim5, kartPuan5.toInt(), image5)
        kartList.add(kart5)

        val image6 = R.drawable.filiusflitwick
        val ev6 = "RAVENCLAW"
        val evPuan6 = "1"
        val kartIsim6 = "Filius Flitwick"
        val kartPuan6 = "10"
        val kart6 = Kart(ev6, evPuan6.toInt(), kartIsim6, kartPuan6.toInt(), image6)
        kartList.add(kart6)

        val image7 = R.drawable.letalestrange
        val ev7 = "SLYTHERİN"
        val evPuan7 = "2"
        val kartIsim7 = "Leta Lestrange"
        val kartPuan7 = "10"
        val kart7 = Kart(ev7, evPuan7.toInt(), kartIsim7, kartPuan7.toInt(), image7)
        kartList.add(kart7)

        val image8 = R.drawable.cedricdiggory
        val ev8 = "HUFFLEPUFF"
        val evPuan8 = "1"
        val kartIsim8 = "Cedric Diggory"
        val kartPuan8 = "18"
        val kart8 = Kart(ev8, evPuan8.toInt(), kartIsim8, kartPuan8.toInt(), image8)
        kartList.add(kart8)

        val image9 = R.drawable.minervamcgonagall
        val ev9 = "GRYFFİNDOR"
        val evPuan9 = "2"
        val kartIsim9 = "Minerva McGonagall"
        val kartPuan9 = "13"
        val kart9 = Kart(ev9, evPuan9.toInt(), kartIsim9, kartPuan9.toInt(), image9)
        kartList.add(kart9)

        val image10 = R.drawable.severussnape
        val ev10 = "SLYTHERİN"
        val evPuan10 = "2"
        val kartIsim10 = "Severus Snape"
        val kartPuan10 = "18"
        val kart10 = Kart(ev10, evPuan10.toInt(), kartIsim10, kartPuan10.toInt(), image10)
        kartList.add(kart10)

        val image11 = R.drawable.rowenaravenclaw
        val ev11 = "RAVENCLAW"
        val evPuan11 = "1"
        val kartIsim11 = "Rowena Ravenclaw"
        val kartPuan11 = "20"
        val kart11 = Kart(ev11, evPuan11.toInt(), kartIsim11, kartPuan11.toInt(), image11)
        kartList.add(kart11)

        val image12 = R.drawable.arthurweasley
        val ev12 = "GRYFFİNDOR"
        val evPuan12 = "2"
        val kartIsim12 = "Arthur Weasley"
        val kartPuan12 = "10"
        val kart12 = Kart(ev12, evPuan12.toInt(), kartIsim12, kartPuan12.toInt(), image12)
        kartList.add(kart12)

        val image13 = R.drawable.myrtlewarren
        val ev13 = "RAVENCLAW"
        val evPuan13 = "1"
        val kartIsim13 = "Myrtle Warren"
        val kartPuan13 = "5"
        val kart13 = Kart(ev13, evPuan13.toInt(), kartIsim13, kartPuan13.toInt(), image13)
        kartList.add(kart13)

        val image14 = R.drawable.tedlupin
        val ev14 = "HUFFLEPUFF"
        val evPuan14 = "1"
        val kartIsim14 = "Ted Lupin"
        val kartPuan14 = "10"
        val kart14 = Kart(ev14, evPuan14.toInt(), kartIsim14, kartPuan14.toInt(), image14)
        kartList.add(kart14)

        val image15 = R.drawable.siriusblack
        val ev15 = "GRYFFİNDOR"
        val evPuan15 = "2"
        val kartIsim15 = "Sirius Black"
        val kartPuan15 = "18"
        val kart15 = Kart(ev15, evPuan15.toInt(), kartIsim15, kartPuan15.toInt(), image15)
        kartList.add(kart15)

        val image16 = R.drawable.ernestmacmillan
        val ev16 = "HUFFLEPUFF"
        val evPuan16 = "1"
        val kartIsim16 = "Ernest Macmillan"
        val kartPuan16 = "5"
        val kart16 = Kart(ev16, evPuan16.toInt(), kartIsim16, kartPuan16.toInt(), image16)
        kartList.add(kart16)

        val image17 = R.drawable.evanrosier
        val ev17 = "SLYTHERİN"
        val evPuan17 = "2"
        val kartIsim17 = "Evan Rosier"
        val kartPuan17 = "10"
        val kart17 = Kart(ev17, evPuan17.toInt(), kartIsim17, kartPuan17.toInt(), image17)
        kartList.add(kart17)

        val image18 = R.drawable.ronweasley
        val ev18 = "GRYFFİNDOR"
        val evPuan18 = "2"
        val kartIsim18 = "Ron Weasley"
        val kartPuan18 = "8"
        val kart18 = Kart(ev18, evPuan18.toInt(), kartIsim18, kartPuan18.toInt(), image18)
        kartList.add(kart18)

        val image19 = R.drawable.dracomalfoy
        val ev19 = "SLYTHERİN"
        val evPuan19 = "2"
        val kartIsim19 = "Draco Malfoy"
        val kartPuan19 = "5"
        val kart19 = Kart(ev19, evPuan19.toInt(), kartIsim19, kartPuan19.toInt(), image19)
        kartList.add(kart19)

        val image20 = R.drawable.harrypotter
        val ev20 = "GRYFFİNDOR"
        val evPuan20 = "2"
        val kartIsim20 = "Harry Potter"
        val kartPuan20 = "10"
        val kart20 = Kart(ev20, evPuan20.toInt(), kartIsim20, kartPuan20.toInt(), image20)
        kartList.add(kart20)

        val image21 = R.drawable.horaceslughorn
        val ev21 = "SLYTHERİN"
        val evPuan21 = "2"
        val kartIsim21 = "Horace Slughorn"
        val kartPuan21 = "12"
        val kart21 = Kart(ev21, evPuan21.toInt(), kartIsim21, kartPuan21.toInt(), image21)
        kartList.add(kart21)

        val image22 = R.drawable.chochang
        val ev22 = "RAVENCLAW"
        val evPuan22 = "1"
        val kartIsim22 = "Cho Chang"
        val kartPuan22 = "11"
        val kart22 = Kart(ev22, evPuan22.toInt(), kartIsim22, kartPuan22.toInt(), image22)
        kartList.add(kart22)

        val image23 = R.drawable.quirinusquirrell
        val ev23 = "RAVENCLAW"
        val evPuan23 = "1"
        val kartIsim23 = "Quirinus Quirrell"
        val kartPuan23 = "15"
        val kart23 = Kart(ev23, evPuan23.toInt(), kartIsim23, kartPuan23.toInt(), image23)
        kartList.add(kart23)

        val image24 = R.drawable.remuslupin
        val ev24 = "GRYFFİNDOR"
        val evPuan24 = "2"
        val kartIsim24 = "Remus Lupin"
        val kartPuan24 = "10"
        val kart24 = Kart(ev24, evPuan24.toInt(), kartIsim24, kartPuan24.toInt(), image24)
        kartList.add(kart24)

        val image25 = R.drawable.silvanuskettleburn
        val ev25 = "HUFFLEPUFF"
        val evPuan25 = "1"
        val kartIsim25 = "Silvanus Kettleburn"
        val kartPuan25 = "12"
        val kart25 = Kart(ev25, evPuan25.toInt(), kartIsim25, kartPuan25.toInt(), image25)
        kartList.add(kart25)

        val image26 = R.drawable.padmapatil
        val ev26 = "RAVENCLAW"
        val evPuan26 = "1"
        val kartIsim26 = "Padma Patil"
        val kartPuan26 = "10"
        val kart26 = Kart(ev26, evPuan26.toInt(), kartIsim26, kartPuan26.toInt(), image26)
        kartList.add(kart26)

        val image27 = R.drawable.sybilltrelawney
        val ev27 = "RAVENCLAW"
        val evPuan27 = "1"
        val kartIsim27 = "Sybill Trelawney"
        val kartPuan27 = "14"
        val kart27 = Kart(ev27, evPuan27.toInt(), kartIsim27, kartPuan27.toInt(), image27)
        kartList.add(kart27)

        val image28 = R.drawable.luciusmalfoy
        val ev28 = "SLYTHERİN"
        val evPuan28 = "2"
        val kartIsim28 = "Lucius Malfoy"
        val kartPuan28 = "12"
        val kart28 = Kart(ev28, evPuan28.toInt(), kartIsim28, kartPuan28.toInt(), image28)
        kartList.add(kart28)

        val image29 = R.drawable.lilypotter
        val ev29 = "GRYFFİNDOR"
        val evPuan29 = "2"
        val kartIsim29 = "Lily Potter"
        val kartPuan29 = "12"
        val kart29 = Kart(ev29, evPuan29.toInt(), kartIsim29, kartPuan29.toInt(), image29)
        kartList.add(kart29)

        val image30 = R.drawable.bellatrixlestrange
        val ev30 = "SLYTHERİN"
        val evPuan30 = "2"
        val kartIsim30 = "Bellatrix Lestrange"
        val kartPuan30 = "13"
        val kart30 = Kart(ev30, evPuan30.toInt(), kartIsim30, kartPuan30.toInt(), image30)
        kartList.add(kart30)

        val image31 = R.drawable.rubeushagrid
        val ev31 = "GRYFFİNDOR"
        val evPuan31 = "2"
        val kartIsim31 = "Rubeus Hagrid"
        val kartPuan31 = "12"
        val kart31 = Kart(ev31, evPuan31.toInt(), kartIsim31, kartPuan31.toInt(), image31)
        kartList.add(kart31)

        val image32 = R.drawable.tomriddle
        val ev32 = "SLYTHERİN"
        val evPuan32 = "2"
        val kartIsim32 = "Tom Riddle"
        val kartPuan32 = "20"
        val kart32 = Kart(ev32, evPuan32.toInt(), kartIsim32, kartPuan32.toInt(), image32)
        kartList.add(kart32)

        val image33 = R.drawable.pomonasprout
        val ev33 = "HUFFLEPUFF"
        val evPuan33 = "1"
        val kartIsim33 = "Pomona Sprout"
        val kartPuan33 = "10"
        val kart33 = Kart(ev33, evPuan33.toInt(), kartIsim33, kartPuan33.toInt(), image33)
        kartList.add(kart33)

        val image34 = R.drawable.andromedatonks
        val ev34 = "SLYTHERİN"
        val evPuan34 = "2"
        val kartIsim34 = "Andromeda Tonks"
        val kartPuan34 = "16"
        val kart34 = Kart(ev34, evPuan34.toInt(), kartIsim34, kartPuan34.toInt(), image34)
        kartList.add(kart34)

        val image35 = R.drawable.marcusbelby
        val ev35 = "RAVENCLAW"
        val evPuan35 = "1"
        val kartIsim35 = "Marcus Belby"
        val kartPuan35 = "10"
        val kart35 = Kart(ev35, evPuan35.toInt(), kartIsim35, kartPuan35.toInt(), image35)
        kartList.add(kart35)

        val image36 = R.drawable.hannahabbott
        val ev36 = "HUFFLEPUFF"
        val evPuan36 = "1"
        val kartIsim36 = "Hannah Abbott"
        val kartPuan36 = "10"
        val kart36 = Kart(ev36, evPuan36.toInt(), kartIsim36, kartPuan36.toInt(), image36)
        kartList.add(kart36)

        val image37 = R.drawable.doloresumbridge
        val ev37 = "SLYTHERİN"
        val evPuan37 = "2"
        val kartIsim37 = "Dolores Umbridge"
        val kartPuan37 = "10"
        val kart37 = Kart(ev37, evPuan37.toInt(), kartIsim37, kartPuan37.toInt(), image37)
        kartList.add(kart37)

        val image38 = R.drawable.newtscamander
        val ev38 = "HUFFLEPUFF"
        val evPuan38 = "1"
        val kartIsim38 = "Newt Scamander"
        val kartPuan38 = "18"
        val kart38 = Kart(ev38, evPuan38.toInt(), kartIsim38, kartPuan38.toInt(), image38)
        kartList.add(kart38)

        val image39 = R.drawable.helgahufflepuff
        val ev39 = "HUFFLEPUFF"
        val evPuan39 = "1"
        val kartIsim39 = "Helga Hufflepuff"
        val kartPuan39 = "20"
        val kart39 = Kart(ev39, evPuan39.toInt(), kartIsim39, kartPuan39.toInt(), image39)
        kartList.add(kart39)

        val image40 = R.drawable.albusdumbledore
        val ev40 = "GRYFFİNDOR"
        val evPuan40 = "2"
        val kartIsim40 = "Albus Dumbledore"
        val kartPuan40 = "20"
        val kart40 = Kart(ev40, evPuan40.toInt(), kartIsim40, kartPuan40.toInt(), image40)
        kartList.add(kart40)

        val image41 = R.drawable.nymphadoratonks
        val ev41 = "HUFFLEPUFF"
        val evPuan41 = "1"
        val kartIsim41 = "Nymphadora Tonks"
        val kartPuan41 = "14"
        val kart41 = Kart(ev41, evPuan41.toInt(), kartIsim41, kartPuan41.toInt(), image41)
        kartList.add(kart41)

        val image42 = R.drawable.fatfriar
        val ev42 = "HUFFLEPUFF"
        val evPuan42 = "1"
        val kartIsim42 = "Fat Friar"
        val kartPuan42 = "12"
        val kart42 = Kart(ev42, evPuan42.toInt(), kartIsim42, kartPuan42.toInt(), image42)
        kartList.add(kart42)

        val image43 = R.drawable.hermionegranger
        val ev43 = "GRYFFİNDOR"
        val evPuan43 = "2"
        val kartIsim43 = "Hermione Granger"
        val kartPuan43 = "10"
        val kart43 = Kart(ev43, evPuan43.toInt(), kartIsim43, kartPuan43.toInt(), image43)
        kartList.add(kart43)

        val image44 = R.drawable.leanne
        val ev44 = "HUFFLEPUFF"
        val evPuan44 = "1"
        val kartIsim44 = "Leanne"
        val kartPuan44 = "10"
        val kart44 = Kart(ev44, evPuan44.toInt(), kartIsim44, kartPuan44.toInt(), image44)
        kartList.add(kart44)

    }
}