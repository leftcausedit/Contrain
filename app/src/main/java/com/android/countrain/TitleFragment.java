package com.android.countrain;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateVMFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.countrain.databinding.FragmentTitleBinding;


public class TitleFragment extends Fragment {

    public TitleFragment() {
        // 空的构造函数
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel;
        myViewModel = ViewModelProviders.of(requireActivity(),new SavedStateVMFactory(requireActivity())).get(MyViewModel.class);
        FragmentTitleBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_title,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //  进入主页按钮
                myViewModel.load_account();
                if(binding.usernamebox.getText().toString().equals(myViewModel.getUsername().getValue().toString())&&binding.passwordbox.getText().toString().equals((myViewModel.getPassword().getValue().toString()))) {
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_titleFragment_to_homePageFragment);
                }else {
                    Toast.makeText(getContext(),R.string.loginerror,Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.getUsername().setValue(binding.usernamebox.getText().toString());
                myViewModel.getPassword().setValue(binding.passwordbox.getText().toString());
                myViewModel.save_account();
            }
        });

        return binding.getRoot();
    }

}
