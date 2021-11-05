package app.sovic.trucklocator.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import app.sovic.trucklocator.R


/**Parent class of all the fragments in this project*/
abstract class BaseFragment<B : ViewBinding>(private val fragmentLayout: Int) : Fragment() {

    private val constraintLayout: ConstraintLayout? by lazy { view?.findViewById(R.id.constraintLayout) as ConstraintLayout }



    /**
     *  Generic ViewBinding of the subclasses
     * */
    abstract val binding: B


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Adding an option to handle the back press in fragment
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }





    /**
     * An abstract function which will be called on the Back button press
     * */
    abstract fun onBackPressed()



//
//    fun handleError(error: PojoErrorResponse) {
//        when (error.errorType) {
//            ERROR_TYPE_API -> handleApiError(error.errorId!!, error.errorMessage)
//            ERROR_TYPE_RETROFIT -> {
//                constraintLayout?.let {
//                    BaseAppCompatActivity().showSnackBar(
//                        error.errorMessage,
//                        it
//                    )
//                }
//            }
//            ERROR_TYPE_EXCEPTION -> {
//                constraintLayout?.let {
//                    BaseAppCompatActivity().showSnackBar(
//                        error.errorMessage,
//                        it
//                    )
//                }
//            }
//        }
//
//    }
//
//    private fun handleApiError(errorId: Int, errorMessage: String) {
//        when (errorId) {
//            6002 -> {
//                addSlidingFragmentBackStack(
//                    requireActivity().supportFragmentManager,
//                    UpdateFragment(), "Update Fragment", R.id.ActivityContainer
//                )
//            }
//            6003, 6019 -> {
//                constraintLayout?.let { BaseAppCompatActivity().showSnackBar(errorMessage, it) }
//            }
//        }
//    }
}