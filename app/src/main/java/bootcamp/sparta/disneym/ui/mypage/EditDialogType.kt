package bootcamp.sparta.disneym.ui.mypage

enum class EditDialogType {
    ID, PW;

    companion object {
        fun from(name: String?): EditDialogType? {
            return EditDialogType.values().find {
                it.name.uppercase() == name?.uppercase()
            }
        }
    }
}