package com.mercadolibre.android.andesui.button.loading

import android.os.Parcel
import android.os.Parcelable
import android.view.View

class SavedState : View.BaseSavedState {
    var isLoading: Boolean = false
    var text: String = ""

    internal constructor(superState: Parcelable?) : super(superState) {}
    private constructor(`in`: Parcel) : super(`in`) {
        isLoading = `in`.readInt() == 1
        text = `in`.readString()
    }

    override fun writeToParcel(out: Parcel?, flags: Int) {
        super.writeToParcel(out, flags)
        out?.writeInt(if (isLoading) 1 else 0)
        out?.writeString(text)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
            override fun createFromParcel(`in`: Parcel): SavedState? {
                return SavedState(`in`)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }
    }
}