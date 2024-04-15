package com.humbur.puzzlefifteen.screen.game

import android.app.AlertDialog
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.humbur.puzzlefifteen.R
import com.humbur.puzzlefifteen.databinding.FragmentGameBinding
import kotlin.math.abs


class GameFragment : Fragment(R.layout.fragment_game), GameContract.View {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: GameContract.Presenter

    private var emptyX = 0
    private var emptyY = 0

    private var moves = 0

    private val cell = Array(4) {
        arrayOfNulls<TextView>(4)
    }


    private var isGameActive = true
    private var timeWhenPaused: Long = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGameBinding.bind(view)
        presenter = GamePresenter(GameModel(), this)

        isGameActive = true

        binding.btnReset.setOnClickListener {
            binding.txtTime.stop()
            binding.txtTime.base = SystemClock.elapsedRealtime()
            binding.txtTime.start()

            moves = 0
            updateMovement(moves)
            presenter.reset()
        }
        binding.btnMenu.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.txtPause.setOnClickListener {
            timeWhenPaused = binding.txtTime.base  - SystemClock.elapsedRealtime()
            binding.txtTime.stop()

            val builder = AlertDialog.Builder(requireContext())
                .setTitle("Paused ")
                .setMessage("Click resume to continue...")
                .setCancelable(false)
                .setPositiveButton("Resume") { dialog, which ->
                    binding.txtTime.base = timeWhenPaused+SystemClock.elapsedRealtime()
                    binding.txtTime.start()
                }
            val dialog = builder.create()
            dialog.show()
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun init() {
        binding.txtTime.start()
    }

    override fun loadViewAndHandleClicks() {
        for (i in 0 until binding.gridLayout.childCount) {
            val y = i / 4 // y koordinata uchun
            val x = i % 4 // x koordinata uchun

            cell[y][x] = binding.gridLayout.getChildAt(i) as TextView
            cell[y][x]?.apply {
                setOnClickListener {
                    if (canMove(y, x) && isGameActive) {
                        swap(y, x)
                        if (isGameOver()) {
                            isGameActive = false
                            gameOver()

                        }
                    }
                }
            }
        }
    }

    //loadGame funksiya qilib olamiz
    override fun loadGame(list: List<Int>) {
        for (i in 0 until binding.gridLayout.childCount) {//for i ning 0 chisidan gridLayout.childCount tigacha sikl ochiladi
            val y = i / 4 // y koordinata uchun
            val x = i % 4 // x koordinata uchun
            if (list[i] == 16) {  //Agar list ning i massivi teng bolsa 16 ga
                cell[y][x]?.visibility =
                    View.INVISIBLE // cell x y larida bosh katakni korinmas qilib quyamiz yani bitta yana xod bor degan manoda
                emptyY = y
                emptyX = x
                continue
            }
            cell[y][x]?.apply { // bu yerda shu visible (Korinish) holatida qilib quyishimiz kerak boladi
                visibility = View.VISIBLE
                text = list[i].toString()
            }
        }
    }

    override fun isGameOver(): Boolean {
        var counter = 1
        for (i in 0 until binding.gridLayout.childCount) {
            val view = binding.gridLayout.getChildAt(i) as TextView
            Log.d("TAG", "isGameOver: ${view.text.toString()}")
            if (view.text.isEmpty())
                break
            if (view.text.toString() == counter.toString())
                counter++
            else break

        }

        return counter == 16
    }

    private fun canMove(clickedY: Int, clickedX: Int): Boolean {
        return (abs(emptyY - clickedY) == 0 && abs(emptyX - clickedX) == 1 || abs(emptyY - clickedY) == 1 && abs(
            emptyX - clickedX
        ) == 0
                )
    }

    private fun swap(clickedY: Int, clickedX: Int) {
        moves++
        updateMovement(moves)

        cell[emptyY][emptyX]?.apply {
            text = cell[clickedY][clickedX]?.text.toString()
            visibility = View.VISIBLE
        }

        cell[clickedY][clickedX]?.apply {
            visibility = View.INVISIBLE
            text = " "
        }

        emptyY = clickedY
        emptyX = clickedX
    }

    private fun updateMovement(moves: Int) {
        binding.txtMovies.text = moves.toString()
    }

    private fun gameOver() {
        binding.txtTime.stop()
        val direction = GameFragmentDirections.actionGameFragmentToGameOverFragment(moves)
        findNavController().navigate(direction)
    }
}
