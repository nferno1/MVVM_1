package nferno1.mvvmpattern.ui.main



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import nferno1.mvvmpattern.databinding.FragmentMainBinding


@Suppress("DEPRECATION")
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels{MainViewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       binding = FragmentMainBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener{
            val login = binding.login.text.toString()
            val password = binding.password.text.toString()
            viewModel.onSignInClick(login, password)
        }

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state
                    .collect{state ->
                      when(state){
                          State.Loading -> {
                              binding.progressBar.isVisible = true
                              binding.loginLayout.error = null
                              binding.passwordLayout.error = null
                              binding.button.isEnabled = false
                          }
                          State.Succes -> {
                              binding.progressBar.isVisible = false
                              binding.loginLayout.error = null
                              binding.passwordLayout.error = null
                              binding.button.isEnabled = true
                          }
                          is State.Error -> {
                              binding.progressBar.isInvisible = false
                              binding.loginLayout.error = state.loginError
                              binding.passwordLayout.error = state.passwordError
                              binding.button.isEnabled = true

                          }

                      }
                    }
            }
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.error
                    .collect{ message ->
                        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
                    }
            }

    }

}