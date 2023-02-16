package com.andreyyurko.dnd.data.characterData

data class Action(
    var name: String,
    var description: String,
    var type: ActionType
)

data class ChargesCounter(
    var current: Int,
    var maximum: Int
)
