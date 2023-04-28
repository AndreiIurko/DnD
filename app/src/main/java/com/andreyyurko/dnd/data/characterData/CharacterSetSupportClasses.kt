package com.andreyyurko.dnd.data.characterData

data class Action(
    var name: String,
    var description: String,
    var type: ActionType,
    var relatedCharges: String,
) {
    constructor(name: String, description: String, type: ActionType) : this(name, description, type, "")
}

data class ChargesCounter(
    var current: Int,
    var maximum: Int
)

data class Note(
    var createDate: String,
    var lastModifiedDate: String,
    var title: String,
    var text: String
)
