package rc.thesis.learningapp

object Constants {

//    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1, R.drawable.ama,
            "A",
            "E",
            "I",
            "O",
            "U",
            1
        )

        questionsList.add(que1)

        val que2 = Question(
            2, R.drawable.apoy,
            "A",
            "E",
            "I",
            "O",
            "U",
            1
        )

        questionsList.add(que2)

        val que3 = Question(
            3, R.drawable.bote,
            "A",
            "E",
            "I",
            "O",
            "U",
            2
        )

        questionsList.add(que3)

        val que4 = Question(
            4, R.drawable.buto,
            "A",
            "E",
            "I",
            "O",
            "U",
            4
        )

        questionsList.add(que4)

        val que5 = Question(
            5, R.drawable.ibon,
            "A",
            "E",
            "I",
            "O",
            "U",
            3
        )

        questionsList.add(que5)

        val que6 = Question(
            6, R.drawable.isa,
            "A",
            "E",
            "I",
            "O",
            "U",
            3
        )

        questionsList.add(que6)

        val que7 = Question(
            7, R.drawable.kape,
            "A",
            "E",
            "I",
            "O",
            "U",
            2
        )

        questionsList.add(que7)

        val que8 = Question(
            8, R.drawable.pera,
            "A",
            "E",
            "I",
            "O",
            "U",
            1
        )

        questionsList.add(que8)

        val que9 = Question(
            9, R.drawable.puso,
            "A",
            "E",
            "I",
            "O",
            "U",
            4
        )

        questionsList.add(que9)

        val que10 = Question(
            10, R.drawable.tabo,
            "A",
            "E",
            "I",
            "O",
            "U",
            4
        )

        questionsList.add(que10)

        val que11 = Question(
            11, R.drawable.susi,
            "A",
            "E",
            "I",
            "O",
            "U",
            3
        )

        questionsList.add(que11)

        val que12 = Question(
            12, R.drawable.usa,
            "A",
            "E",
            "I",
            "O",
            "U",
            5
        )

        questionsList.add(que12)

        val que13 = Question(
            13, R.drawable.ubas,
            "A",
            "E",
            "I",
            "O",
            "U",
            5
        )

        questionsList.add(que13)

        val que14 = Question(
            14, R.drawable.atis,
            "A",
            "E",
            "I",
            "O",
            "U",
            1
        )

        questionsList.add(que14)

        val que15 = Question(
            15, R.drawable.oso,
            "A",
            "E",
            "I",
            "O",
            "U",
            4
        )

        questionsList.add(que15)



        questionsList.shuffle()
        return questionsList
    }

    fun getQuestions2(): ArrayList<Question> {
        val questionsList2 = ArrayList<Question>()

        val que16 = Question(
            16, R.drawable.asotanong,
            "SE",
            "SU",
            "SA",
            "SI",
            "SO",
            5
        )

        questionsList2.add(que16)

        val que17 = Question(
            17, R.drawable.bakatanong,
            "KU",
            "KI",
            "KA",
            "KO",
            "KE",
            3
        )

        questionsList2.add(que17)

        val que18 = Question(
            18, R.drawable.basotanong,
            "SE",
            "SO",
            "SU",
            "SI",
            "SA",
            2
        )

        questionsList2.add(que18)

        val que19 = Question(
            19, R.drawable.bolatanong,
            "LE",
            "LU",
            "LO",
            "LA",
            "LI",
            4
        )

        questionsList2.add(que19)

        val que20 = Question(
            20, R.drawable.dagatanong,
            "GI",
            "GA",
            "GU",
            "GO",
            "GE",
            2
        )

        questionsList2.add(que20)

        val que21 = Question(
            21, R.drawable.damotanong,
            "MO",
            "MI",
            "MU",
            "MA",
            "ME",
            1
        )

        questionsList2.add(que21)

        val que22 = Question(
            22, R.drawable.isdatanong,
            "DI",
            "DU",
            "DA",
            "DE",
            "DO",
            3
        )

        questionsList2.add(que22)

        val que23 = Question(
            23, R.drawable.matatanong,
            "TO",
            "TU",
            "TI",
            "TA",
            "TE",
            4
        )

        questionsList2.add(que23)

        val que24 = Question(
            24, R.drawable.patotanong,
            "TI",
            "TU",
            "TA",
            "TE",
            "TO",
            5
        )

        questionsList2.add(que24)

        val que25 = Question(
            25, R.drawable.pusatanong,
            "SA",
            "SO",
            "SI",
            "SE",
            "SU",
            1
        )

        questionsList2.add(que25)

        val que26 = Question(
            26, R.drawable.bibetanong,
            "BA",
            "BO",
            "BI",
            "BE",
            "BU",
            4
        )

        questionsList2.add(que26)

        val que27 = Question(
            27, R.drawable.kesotanong,
            "KU",
            "KI",
            "KO",
            "KE",
            "KA",
            4
        )

        questionsList2.add(que27)

        val que28 = Question(
            28, R.drawable.maistanong,
            "MU",
            "MI",
            "MO",
            "ME",
            "MA",
            5
        )

        questionsList2.add(que28)

        val que29 = Question(
            29, R.drawable.mesatanong,
            "MO",
            "MU",
            "MI",
            "MA",
            "ME",
            5
        )

        questionsList2.add(que29)

        val que30 = Question(
            30, R.drawable.yoyotanong,
            "YO",
            "YI",
            "YU",
            "YA",
            "YE",
            1
        )

        questionsList2.add(que30)

        questionsList2.shuffle()
        return questionsList2
    }

    fun getQuestions3(): ArrayList<Question> {
        val questionsList3 = ArrayList<Question>()

        val que31 = Question(
            31, R.drawable.ahasq3,
            "A",
            "YA",
            "BU",
            "HI",
            "GE",
            1
        )

        questionsList3.add(que31)

        val que32 = Question(
            32, R.drawable.arawq3,
            "E",
            "TE",
            "A",
            "SI",
            "WA",
            3
        )

        questionsList3.add(que32)

        val que33 = Question(
            33, R.drawable.bahayq3,
            "O",
            "BE",
            "A",
            "SI",
            "BA",
            5
        )

        questionsList3.add(que33)

        val que34 = Question(
            34, R.drawable.damitq3,
            "SU",
            "ME",
            "DU",
            "DA",
            "O",
            4
        )

        questionsList3.add(que34)

        val que35 = Question(
            35, R.drawable.ekisq3,
            "PA",
            "E",
            "A",
            "GA",
            "I",
            2
        )

        questionsList3.add(que35)

        val que36 = Question(
            36, R.drawable.ilongq3,
            "RA",
            "U",
            "DA",
            "LE",
            "I",
            5
        )

        questionsList3.add(que36)

        val que37 = Question(
            37, R.drawable.kamayq3,
            "KA",
            "SU",
            "LI",
            "U",
            "PA",
            1
        )

        questionsList3.add(que37)

        val que38 = Question(
            38, R.drawable.lamokq3,
            "LI",
            "LA",
            "YO",
            "U",
            "PU",
            2
        )

        questionsList3.add(que38)

        val que39 = Question(
            39, R.drawable.lapisq3,
            "I",
            "SU",
            "LA",
            "SA",
            "A",
            3
        )

        questionsList3.add(que39)

        val que40 = Question(
            40, R.drawable.ngipinq3,
            "WU",
            "NGE",
            "E",
            "NGI",
            "I",
            4
        )

        questionsList3.add(que40)

        val que41 = Question(
            41, R.drawable.orasanq3,
            "U",
            "NGA",
            "YO",
            "PA",
            "O",
            5
        )

        questionsList3.add(que41)

        val que42 = Question(
            42, R.drawable.ipisq3,
            "LA",
            "I",
            "MI",
            "NGI",
            "A",
            2
        )

        questionsList3.add(que42)

        val que43 = Question(
            43, R.drawable.pagongq3,
            "PA",
            "E",
            "SA",
            "KI",
            "LA",
            1
        )

        questionsList3.add(que43)

        val que44 = Question(
            44, R.drawable.tinapayq3,
            "TI",
            "SI",
            "BA",
            "NGI",
            "U",
            1
        )

        questionsList3.add(que44)

        val que45 = Question(
            45, R.drawable.ulanq3,
            "U",
            "I",
            "GU",
            "NGA",
            "SO",
            1
        )

        questionsList3.add(que45)

        questionsList3.shuffle()
        return questionsList3
    }
}