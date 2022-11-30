package com.android.countrain;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateVMFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.android.countrain.databinding.FragmentChooseBinding;

public class ChooseFragment extends Fragment {

    public ChooseFragment() {
        // 空的构造函数
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel;  //  使用自己定义的ViewModel
        myViewModel = ViewModelProviders.of(requireActivity(), new SavedStateVMFactory(requireActivity())).get(MyViewModel.class);
        final FragmentChooseBinding binding; //  进行数据绑定
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        myViewModel.setQuestionType(0);  //  分别用0~4对应五种运算方式，默认设置为加法
/*
        binding.radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { //  判断选择的运算方式
                switch (checkedId) {
                    case R.id.radioButton:
                        myViewModel.setQuestionType(0);
                        break;
                    case R.id.radioButton2:
                        myViewModel.setQuestionType(1);
                        break;
                    case R.id.radioButton3:
                        myViewModel.setQuestionType(2);
                        break;
                    case R.id.radioButton5:
                        myViewModel.setQuestionType(3);
                        break;
                    case R.id.radioButton4:
                        myViewModel.setQuestionType(4);
                        break;
                }
            }
        });
*/
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
/*                String str = (String) binding.spinner.getSelectedItem();
                Toast.makeText(getContext(), "选择了" + str, Toast.LENGTH_SHORT).show();*/
                switch ((int)id) {
                    case 0:
                        myViewModel.setQuestionType(0);
                        break;
                    case 1:
                        myViewModel.setQuestionType(1);
                        break;
                    case 2:
                        myViewModel.setQuestionType(2);
                        break;
                    case 3:
                        myViewModel.setQuestionType(3);
                        break;
                    case 4:
                        myViewModel.setQuestionType(4);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("TAG_MainActivity", parent.toString());
            }
        });

        myViewModel.setDataType(0);  //  0和1分别对应整数和小数两种数据类型，    默认的数据类型为整数
/*        binding.radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {  //  判断选择的数据类型
                switch (checkedId) {
                    case R.id.radioButton6:
                        myViewModel.setDataType(0);
                        break;
                    case R.id.radioButton7:
                        myViewModel.setDataType(1);
                        break;
                }
            }
        });*/
/*        binding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String str = (String) binding.spinner.getSelectedItem();
//                Toast.makeText(getContext(), "选择了" + str, Toast.LENGTH_SHORT).show();
                switch ((int)id) {
                    case 0:
                        myViewModel.setDataType(0);
                        break;
                    case 1:
                        myViewModel.setDataType(1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("TAG_MainActivity", parent.toString());
            }
        });*/

        myViewModel.setTotalQuestionNumber(10);  //  默认选择的题目数量为10
        binding.seekBar.setProgress(10);
        String s = getString(R.string.choose_question_number) + "10";
        binding.textView3.setText(s);  //  让seekbar及textview控件都显示为10
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                {
                    String s = getString(R.string.choose_question_number) + progress;  //  设置监听事件，获取设置的题目数量
                    binding.textView3.setText(s);
                    myViewModel.setTotalQuestionNumber(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // 为进入测试按键设置监听事件
                String s = binding.editText.getText().toString();  //  获取输入的数字范围
                if (s.length() > 5) {  //  输入数字位数过多，为避免产生int型数据的溢出，设置默认值为100000，并弹出Toast提示用户
                    s = "99999";
                    Toast.makeText(getActivity(), getString(R.string.toast_data_overflow), Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(s)) {  //  输入为空，设置默认值为100，并弹出Toast提示用户
                    s = "100";
                    Toast.makeText(getActivity(), getString(R.string.toast_data_empty), Toast.LENGTH_SHORT).show();
                }
                if (Integer.valueOf(s) > 100000) {  //  输入的数字过大，为避免用户非法操作，设置默认值为100000，并弹出Toast提示用户
                    s = "99999";
                    Toast.makeText(getActivity(), getString(R.string.toast_data_overflow), Toast.LENGTH_SHORT).show();
                }
                if (Integer.valueOf(s) < 10) {  //  输入数据过小，不符合实际，设置值为10，并弹出Toast提示用户
                    s = "10";
                    Toast.makeText(getActivity(), getString(R.string.toast_data_small), Toast.LENGTH_SHORT).show();
                }
                myViewModel.setDataSize(Integer.valueOf(s));  //  设置最终的数据范围
                NavController controller = Navigation.findNavController(v);  //  设置页面跳转的动作
                controller.navigate(R.id.action_chooseFragment_to_questionFragment);
            }
        });

        return binding.getRoot();
    }
}
