package com.android.countrain;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.android.countrain.databinding.FragmentHistoryBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    public HistoryFragment() {
        // 空的构造函数
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final MyViewModel myViewModel;  //  使用自己定义的ViewModel
        myViewModel = ViewModelProviders.of(requireActivity(),new SavedStateVMFactory(requireActivity())).get(MyViewModel.class);
        final FragmentHistoryBinding binding;  //  使用databinding管理数据
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_history,container,false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        binding.button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //  为重置按钮设置监听事件
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  //  点击重置按钮后弹出一个对话框
                builder.setTitle(getString(R.string.history_reset_dialog));
                builder.setPositiveButton(getString(R.string.reset_dialog_positive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {  //  设置yes选项
                        myViewModel.clear();  //  点击确定后调用clear函数
                        Toast.makeText(getActivity(),getString(R.string.reset_indicator),Toast.LENGTH_SHORT).show();  //  并弹出toast提示清除成功
                        Integer i = myViewModel.load_total_number();
                        String str = i.toString();
                        i = myViewModel.load_total_mistake();
                        String str2 = i.toString();
                        binding.tvTotalNumber.setText("总做题数："+str);  //  清除后重新计算并设置数据的值
                        binding.tvTimes2.setText("总错题数："+str2);
                        binding.tvAccuary.setText("错误率："+"0%");
                    }
                });
                builder.setNegativeButton(getString(R.string.dialog_negative_message), new DialogInterface.OnClickListener() {  //  设置取消选项
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //  点击取消按钮不做操作
                    }
                });
                AlertDialog dialog = builder.create();  //  弹出对话框
                dialog.show();
            }
        });
        //  每次进入页面时都重新计算并设置各项数据的值
        Integer i = myViewModel.load_total_number();
        String str = i.toString();
        binding.tvTotalNumber.setText("总做题数："+str);
        i = myViewModel.load_total_mistake();
        String str2 = i.toString();
        binding.tvTimes2.setText("总错题数："+str2);

        if (myViewModel.load_total_number()==0){
            binding.tvAccuary.setText("错误率："+"0%");
        }else{
            double d = (double)myViewModel.load_total_mistake()*100/(double)myViewModel.load_total_number();
            int j = (int)Math.rint(d);
            binding.tvAccuary.setText("错误率："+ j +"%");
        }


        return binding.getRoot();
    }
}
