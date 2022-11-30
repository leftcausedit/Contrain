package com.android.countrain;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateVMFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.countrain.databinding.FragmentHomePageBinding;


public class HomePageFragment extends Fragment {

    public HomePageFragment() {
        // 空的构造函数
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel;  //  使用自己定义的ViewModel
        myViewModel = ViewModelProviders.of(requireActivity(),new SavedStateVMFactory(requireActivity())).get(MyViewModel.class);
        FragmentHomePageBinding binding;  //  使用databinding管理数据
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_page,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        binding.button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //  进入测试按钮
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_homePageFragment_to_chooseFragment);  //  跳转到选择页面
            }
        });

        binding.button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //  错题集按钮
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_homePageFragment_to_mistakesFragment2);  //  跳转到错题本页面
            }
        });
        binding.button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //  历史记录按钮
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_homePageFragment_to_historyFragment);  //  跳转到历史记录页面
            }
        });

        return binding.getRoot();
    }

}
