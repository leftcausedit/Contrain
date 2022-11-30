package com.android.countrain;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateVMFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.countrain.databinding.FragmentMistakesBinding;


public class MistakesFragment extends Fragment {

    public MistakesFragment() {
        // 空的构造函数
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel;  //  使用自己定义的ViewModel
        myViewModel = ViewModelProviders.of(requireActivity(),new SavedStateVMFactory(requireActivity())).get(MyViewModel.class);
        final FragmentMistakesBinding binding;  //  进行数据绑定
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_mistakes,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.textView19.setMovementMethod(ScrollingMovementMethod.getInstance());  //  将TextView控件设置为滚动
//        binding.textView19.setText(R.string.mistakes_mistakes_file);
        if(myViewModel.is_empty_mistake() == false ){  //  判断当前有没有错题，无错题则提示
            binding.textView19.setText(myViewModel.load_mistake());  //  调用myViewModel中的读取错题函数来显示错题
        }



        return binding.getRoot();
    }
}
