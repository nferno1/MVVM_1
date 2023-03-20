package nferno1.mvvmpattern.ui.main


import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MainViewModelFactory() : ViewModelProvider.Factory, Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel (MainRepository()) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainViewModelFactory> {
        override fun createFromParcel(parcel: Parcel): MainViewModelFactory {
            return MainViewModelFactory(parcel)
        }

        override fun newArray(size: Int): Array<MainViewModelFactory?> {
            return arrayOfNulls(size)
        }
    }

}