package com.android.countrain;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateVMFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.countrain.databinding.FragmentFinishBinding;


public class FinishFragment extends Fragment {

    public FinishFragment() {
        // 空的构造函数
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel myViewModel;  //  使用自己定义的ViewModel
        myViewModel = ViewModelProviders.of(requireActivity(),new SavedStateVMFactory(requireActivity())).get(MyViewModel.class);
        FragmentFinishBinding binding;  //  进行数据绑定
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_finish,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        //  用错题数除以总题目数来计算错误率
        double d = (double)myViewModel.getIncorrectNumber().getValue()*100/(double)myViewModel.getTotalQuestionNumber().getValue();
        int i = (int)Math.rint(d);  //  将其四舍五入
        binding.textView14.setText("错误率："+ i +"%");  //  设置textView的内容
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //  为返回按钮添加监听事件，跳转到主页
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_finishFragment_to_homePageFragment);
            }
        });
        binding.button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //  为查看错题按钮添加监听事件，跳转到错题集
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_finishFragment_to_mistakesFragment2);
            }
        });
        return binding.getRoot();
    }
}
