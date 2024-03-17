package cat.pedralbes.akashi.melodicmemory.system

import java.util.*

class SimonGame (private val numButtons: Int){
    private val random = Random()
    private val sequence: MutableList<Int> = mutableListOf()

    // Add new num in sequence;
    fun generateNextSequence() {
        sequence.add(random.nextInt(numButtons))
    }

    // Get current sequence;
    fun getSequence(): List<Int> {
        return  sequence
    }

    // Restart game;
    fun restart(){
        sequence.clear()
    }
}