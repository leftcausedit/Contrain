package com.android.countrain;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateVMFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.countrain.databinding.FragmentQuestionBinding;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {


    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel;  //  使用自己定义的ViewModel
        myViewModel = ViewModelProviders.of(requireActivity(), new SavedStateVMFactory(requireActivity())).get(MyViewModel.class);
        myViewModel.setCurrentQuestionNumber(1);
        myViewModel.generator();
        final FragmentQuestionBinding binding;  //  使用DataBinding来管理数据
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.timer.setBase(SystemClock.elapsedRealtime());  //  计时清零
        final int[] point_flag = {0};  //  小数点标志位
        final int[] error_flag = {0};  //  错误标志位
        //计时开始
        binding.timer.start();
        final StringBuilder builder = new StringBuilder();  //  利用StringBuilder来构造字符串

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // 为按钮设置监听事件
                switch (v.getId()) {
                    case R.id.button14:
                        builder.append("0");
                        break;
                    case R.id.button4:
                        builder.append("1");
                        break;
                    case R.id.button5:
                        builder.append("2");
                        break;
                    case R.id.button6:
                        builder.append("3");
                        break;
                    case R.id.button7:
                        builder.append("4");
                        break;
                    case R.id.button8:
                        builder.append("5");
                        break;
                    case R.id.button9:
                        builder.append("6");
                        break;
                    case R.id.button10:
                        builder.append("7");
                        break;
                    case R.id.button11:
                        builder.append("8");
                        break;
                    case R.id.button12:
                        builder.append("9");
                        break;
                    case R.id.button13:  // 如果按了清零键
                        if (builder.length() != 0) {  //  如果editText中有输入
                            if (builder.toString().charAt(builder.length() - 1) == '.') {
                                point_flag[0]--;  //  删去的是小数点，则小数点标志位-1
                            }
                            builder.setLength(builder.length() - 1);  //  设置字符串长度-1
                        } else {  //  当前是空串
                            builder.setLength(0);  // 字符串长度仍设为0
                        }
                        break;
                    case R.id.button16: // 小数点
                        if (point_flag[0] != 1) {  //  如果当前字符串中没有小数点
                            builder.append(".");  //  在末尾添加一个小数点
                            point_flag[0]++;  //  小数点标志位+1
                            break;
                        }
                }
                if (builder.length() == 0) {  //  利用TextView控件显示输入的内容
                    binding.textView15.setText(getString(R.string.question_input_indicator));
                } else {
                    binding.textView15.setText(builder.toString());
                }
            }
        };


        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.button10.setOnClickListener(listener);
        binding.button11.setOnClickListener(listener);
        binding.button12.setOnClickListener(listener);
        binding.button13.setOnClickListener(listener);
        binding.button14.setOnClickListener(listener);
        binding.button16.setOnClickListener(listener);
        myViewModel.getCorrectNumber().setValue(0);
        myViewModel.getIncorrectNumber().setValue(0);
        //  按了提交按键
        binding.button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point_flag[0] = 0;  //  将小数点标志位设为0
                boolean zero_flag = true;
                for (int i = 0 ; i < builder.toString().length() ; i++){
                    if (builder.toString().charAt(i) != '0')
                        zero_flag = false;
                }
                if(zero_flag){
                    builder.setLength(0);
                }
                if (builder.length() == 0) {  //  如果输入的是空字符串
                    builder.append("-818");
                }
                if (builder.toString().charAt(0) == '.')  //  第一位是小数点，则在前面补0
                    builder.insert(0, "0");
                if (builder.toString().charAt(builder.toString().length() - 1) == '.')  //最后一位是小数点则在后面补0
                    builder.append("0");
                while (builder.toString().charAt(0) == '0' && builder.toString().charAt(1) != '.' && builder.toString().length()>1) {  //  第一位是0且第二位不是小数点，则把首位的0去掉
                    builder.delete(0, 1);
                }

                if (Float.valueOf(builder.toString()).floatValue() == myViewModel.getAnswer().getValue()) {
                    myViewModel.answerCorrect();
                    builder.setLength(0);
                   /* Toast.makeText(getContext(),R.string.question_answer_false_message,Toast.LENGTH_SHORT).show();
                    binding.textView15.setText("0");*/
                    binding.textView15.setText(R.string.question_answer_correct_message);  //  显示回答正确
                    //判断是否已经答题结束
                    if (myViewModel.getFinishedQuestionNumber().getValue() >= myViewModel.getTotalQuestionNumber().getValue()) {
                        NavController controller = Navigation.findNavController(v);
                        binding.timer.stop();
                        myViewModel.getCurrentTime().setValue(binding.timer.getText().toString());
                        myViewModel.getCorrectNumber().setValue(myViewModel.getCorrectNumber().getValue()+1);
                        myViewModel.save();
                        if (error_flag[0] == 1){  //  错题记录完成后记录日期
                            Calendar calendar = Calendar.getInstance();
                            String year = String.valueOf(calendar.get(Calendar.YEAR));
                            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                            String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                            String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
                            String minute = String.valueOf(calendar.get(Calendar.MINUTE));
                            if(minute.length()==1) minute="0"+minute;
                            if (hour.length()==1) hour="0"+hour;
                            String mistake_date = "日期：" + year + "年" + month + "月" + day + "日" +   "\n" + "时间：" + hour + "时" + minute + "分" + "\n";
                            myViewModel.getMistakes().setValue(""+mistake_date);
                            myViewModel.save_mistake();
                        }
                        error_flag[0] = 0;  //  错误标志位设为0
                        controller.navigate(R.id.action_questionFragment_to_finishFragment);
                    }
                } else {
                    if(builder.toString().equals("-818")){
                        builder.delete(0,5);
                        builder.append(" ?");
                    }
                    //错题集
                    if (error_flag[0] == 0) {  //  本轮测试中第一次出现错误

                        String left_number = myViewModel.getLeftNumberString().getValue();
                        String right_number = myViewModel.getRightNumberString().getValue();
                        String operator = myViewModel.getOperator().getValue();
                        String error_answer = builder.toString();
                        String current_mistake = left_number + operator + right_number + "=" + error_answer + "\n\n";
                        myViewModel.getMistakes().setValue(current_mistake);
                        myViewModel.getIncorrectNumber().setValue(myViewModel.getIncorrectNumber().getValue()+1);
                        myViewModel.save_mistake();
                        error_flag[0] = 1;

                    } else {  //  本轮测试中不是第一次出现错误
                        String left_number = myViewModel.getLeftNumberString().getValue();
                        String right_number = myViewModel.getRightNumberString().getValue();
                        String operator = myViewModel.getOperator().getValue();
                        String error_answer = builder.toString();
                        String current_mistake = left_number + operator + right_number + "=" + error_answer + "\n";
                        myViewModel.getMistakes().setValue(myViewModel.getMistakes().getValue() + current_mistake);
                        myViewModel.getIncorrectNumber().setValue(myViewModel.getIncorrectNumber().getValue()+1);
                        error_flag[0] = 1;
                        myViewModel.save_mistake();
                    }

                    String left_number = myViewModel.getLeftNumberString().getValue();
                    String right_number = myViewModel.getRightNumberString().getValue();
                    String operator = myViewModel.getOperator().getValue();
                    String right_answer = myViewModel.getAnswer().getValue().toString();
                    String right_formula = "回答错误," + left_number + operator + right_number + "=" + right_answer;
                    myViewModel.answerFalse();
                    builder.setLength(0);
/*                    Toast.makeText(getContext(),R.string.question_answer_false_message,Toast.LENGTH_SHORT).show();
                    binding.textView15.setText("0");*/

                    binding.textView15.setText(right_formula);  //  显示回答错误

                    //  判断是否已经答题结束
                    if (myViewModel.getFinishedQuestionNumber().getValue() >= myViewModel.getTotalQuestionNumber().getValue()) {
                        NavController controller = Navigation.findNavController(v);
                        binding.timer.stop();
                        myViewModel.getCurrentTime().setValue(binding.timer.getText().toString());
                        myViewModel.save();
                        if (error_flag[0] == 1){  //  错题记录完成后记录日期
                            Calendar calendar = Calendar.getInstance();
                            String year = String.valueOf(calendar.get(Calendar.YEAR));
                            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                            String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                            String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
                            String minute = String.valueOf(calendar.get(Calendar.MINUTE));
                            if(minute.length()==1) minute="0"+minute;
                            if (hour.length()==1) hour="0"+hour;
                            String mistake_date = "日期：" + year + "年" + month + "月" + day + "日" +   "\n" + "时间：" + hour + "时" + minute + "分" + "\n";
                            myViewModel.getMistakes().setValue(""+mistake_date);
                            myViewModel.save_mistake();
                        }
                        error_flag[0] = 0;  //  错误标志位设为0
                        controller.navigate(R.id.action_questionFragment_to_finishFragment);  //  页面跳转
                    }
                }

            }
        });
        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_question, container, false);
    }


}
