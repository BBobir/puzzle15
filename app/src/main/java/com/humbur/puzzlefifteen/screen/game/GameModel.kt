package com.humbur.puzzlefifteen.screen.game

class GameModel : GameContract.Model {

    private val isTestingMode = true
    private val list: MutableList<Int> = ArrayList()


    override fun loadNumbers(): List<Int> {
        for (i in 1..16) {
            list.add(i)
        }
        return list
    }

    override fun getShuffledNumbers(): List<Int> {
        if (isTestingMode) {
            return list
        } else {

            do {

                list.shuffle()
            } while (!isSolvable(list))

        }
        return list
    }

    private fun isSolvable(list: List<Int>): Boolean {
        var counter = 0
        for (i in list.indices) {
            if (list[i] == 16) {
                counter += i / 4 + 1
                continue
            }
            for (j in i + 1 until list.size) {
                if (list[i] > list[j]) {
                    counter++
                }
            }
        }

        return counter % 2 == 0
    }


}