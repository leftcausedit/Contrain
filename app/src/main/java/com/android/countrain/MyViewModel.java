package com.android.countrain;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import java.math.BigDecimal;
import java.util.Random;

public class MyViewModel extends AndroidViewModel {
    private SavedStateHandle handle;
    //管理当前题目数
    private static String KEY_CURRENT_QUESTION_NUMBER = "key_current_question_number";
    //管理已做题数
    private static String KEY_FINISHED_QUESTION_NUMBER = "key_finished_question_number";
    //管理题目与答案
    private static String KEY_OPERATOR = "key_operator";
    private static String KEY_LEFT_NUMBER = "key_left_number";
    private static String KEY_RIGHT_NUMBER = "key_right_number";
    private static String KEY_LEFT_NUMBER_STRING = "key_left_number_string";
    private static String KEY_RIGHT_NUMBER_STRING = "key_right_number_string";
    private static String KEY_ANSWER = "key_answer";
    //管理题目类型
    private static String KEY_TOTAL_QUESTION_NUMBER = "key_total_question_number";
    private static String KEY_QUESTION_TYPE = "key_question_type";
    private static String KEY_DATA_TYPE = "key_data_type";
    private static String KEY_DATA_SIZE = "key_data_size";
    //管理错题
    private static String KEY_CURRENT_MISTAKES = "key_current_mistakes";
    private static String KEY_MISTAKES = "key_mistakes";
    //管理做题时间
    private static String KEY_CURRENT_TIME = "key_current_time";
    //管理做题次数
    private static String KEY_FINISHED_TIMES = "key_finished_times";
    //获取记录的记录名
    private static String SAVE_SHP_DATA_NAME = "save_shp_data_name";
    private static String KEY_CORRECT_NUMBER = "key_correct_number";
    private static String KEY_INCORRECT_NUMBER = "key_incorrect_number";
    private static String KEY_TOTAL_NUMBER = "key_total_number";
    private static String KEY_TOTAL_MISTAKES = "key_total_mistakes";
    private static String KEY_USERNAME = "admin";
    private static String KEY_PASSWORD = "passwd";

    public MyViewModel(@NonNull Application application, SavedStateHandle handle){
        super(application);
        if (!handle.contains(KEY_FINISHED_TIMES)){  //如果第一次创建，则设置默认值
            SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME, Context.MODE_PRIVATE);
            handle.set(KEY_FINISHED_TIMES,shp.getInt(KEY_FINISHED_TIMES,0));
            handle.set(KEY_OPERATOR, "+");
            handle.set(KEY_LEFT_NUMBER, 0);
            handle.set(KEY_RIGHT_NUMBER, 0);
            handle.set(KEY_LEFT_NUMBER_STRING, 0);
            handle.set(KEY_RIGHT_NUMBER_STRING, 0);
            handle.set(KEY_ANSWER, 0);
            handle.set(KEY_CURRENT_QUESTION_NUMBER, 0);
            handle.set(KEY_CURRENT_TIME,0);
            handle.set(KEY_FINISHED_QUESTION_NUMBER,0);
            handle.set(KEY_TOTAL_QUESTION_NUMBER, 0);
            handle.set(KEY_QUESTION_TYPE, 0);
            handle.set(KEY_DATA_SIZE, 0);
            handle.set(KEY_DATA_TYPE, 0);
            handle.set(KEY_CURRENT_MISTAKES, 0);
            handle.set(KEY_MISTAKES,0);
            handle.set(KEY_CORRECT_NUMBER,0);
            handle.set(KEY_INCORRECT_NUMBER,0);
            handle.set(KEY_TOTAL_NUMBER,0);
            handle.set(KEY_TOTAL_MISTAKES,0);
            handle.set(KEY_USERNAME,"admin");
            handle.set(KEY_PASSWORD,"passwd");
        }
        this.handle = handle;
    }
    //获取数据方法
    public MutableLiveData<String> getUsername() { return  handle.getLiveData(KEY_USERNAME);}
    public MutableLiveData<String> getPassword() { return  handle.getLiveData(KEY_PASSWORD);}
    public MutableLiveData<Float> getLeftNumber() {
        return handle.getLiveData(KEY_LEFT_NUMBER);
    }

    public MutableLiveData<Float> getRightNumber() {
        return handle.getLiveData(KEY_RIGHT_NUMBER);
    }

    public MutableLiveData<String> getLeftNumberString() {
        return handle.getLiveData(KEY_LEFT_NUMBER_STRING);
    }

    public MutableLiveData<String > getRightNumberString() {
        return handle.getLiveData(KEY_RIGHT_NUMBER_STRING);
    }

    public MutableLiveData<String > getCurrentTime() {
        return handle.getLiveData(KEY_CURRENT_TIME);
    }

    MutableLiveData<String> getMistakes() {
        return handle.getLiveData(KEY_MISTAKES);
    }

    public MutableLiveData<String> getOperator() {
        return handle.getLiveData(KEY_OPERATOR);
    }

    MutableLiveData<Float> getAnswer() {
        return handle.getLiveData(KEY_ANSWER);
    }

    MutableLiveData<Integer> getFinishedQuestionNumber() {
        return handle.getLiveData(KEY_FINISHED_QUESTION_NUMBER);
    }
    public void setFinishedQuestionNumber(int i) {
        getFinishedQuestionNumber().setValue(i);
    }

    public MutableLiveData<Integer> getCurrentQuestionNumber() {
        return handle.getLiveData(KEY_CURRENT_QUESTION_NUMBER);
    }
    void setCurrentQuestionNumber(int i) {
        getCurrentQuestionNumber().setValue(i);
    }

    public MutableLiveData<Integer> getTotalQuestionNumber() {
        return handle.getLiveData(KEY_TOTAL_QUESTION_NUMBER);
    }

    void setTotalQuestionNumber(int i) {
        getTotalQuestionNumber().setValue(i);
    }

    private MutableLiveData<Integer> getQuestionType() {
        return handle.getLiveData(KEY_QUESTION_TYPE);
    }

    void setQuestionType(int i){
        getQuestionType().setValue(i);
    }

    private MutableLiveData<Integer> getDataType() {
        return handle.getLiveData(KEY_DATA_TYPE);
    }
    void setDataType(int i) {
        getDataType().setValue(i);
    }

    private MutableLiveData<Integer> getDataSize() {
        return handle.getLiveData(KEY_DATA_SIZE);
    }

    void setDataSize(int i) {
        getDataSize().setValue(i);
    }

    public MutableLiveData<Integer> getCurrentMistakes() {
        return handle.getLiveData(KEY_CURRENT_MISTAKES);
    }
    public void setCurrentMistakes(int i) {
        getCurrentQuestionNumber().setValue(i);
    }

    public MutableLiveData<Integer> getFinishedTimes() {
        return handle.getLiveData(KEY_FINISHED_TIMES);
    }

    public void setFinishedTimes(int i) {
        getFinishedTimes().setValue(i);
    }

    public MutableLiveData<Integer> getCorrectNumber(){
        return handle.getLiveData(KEY_CORRECT_NUMBER);
    }

    public MutableLiveData<Integer> getIncorrectNumber(){
        return handle.getLiveData(KEY_INCORRECT_NUMBER);
    }

    public MutableLiveData<Integer> getTotalMistakes(){
        return handle.getLiveData(KEY_TOTAL_MISTAKES);
    }


    void generator(){  //  生成题目的方法
        int LEVEL = getDataSize().getValue();  //  最大数字范围
        double LEVEL_sqrt = Math.sqrt((double) LEVEL);// 最大数字范围取根号，便于控制乘除法时结果不会溢出
        char[] Operator = {'+', '-', '×', '÷'};// 运算符数组
        int Operator_num = getQuestionType().getValue();  //  获取运算符在运算符数组中的序号
        Random random = new Random();//创建随机数对象
        if (Operator_num == 4) {//当用户选择随机出题类型时
            Operator_num = random.nextInt(4);//随机获取运算符序号
        }
        getOperator().setValue(String.valueOf(Operator[Operator_num]));  //  设置操作符
        float x = 0, y = 0;//操作数均为浮点类型
        if (getDataType().getValue() == 1) {//数据类型为浮点数
            //最多1位小数
            int rank;//小数点后的数值
            if(Operator_num == 0) {//加法
                rank = random.nextInt(10);//获取小数位上的值，0~9
                getLeftNumberString().setValue(random.nextInt(LEVEL / 2) + "." + rank);
                //在界面上以字符串形式设置左操作数，避免上下溢出情况
                //将范围设定为LEVEL / 2，确保和不会溢出LEVEL
                x = Float.parseFloat(getLeftNumberString().getValue());//将左操作数值存入x
                rank = random.nextInt(10);
                getRightNumberString().setValue(random.nextInt(LEVEL / 2) + "." + rank);
                y = Float.parseFloat(getRightNumberString().getValue());//获取右操作数
            }else if(Operator_num == 1) {//减法
                rank = random.nextInt(10);
                getLeftNumberString().setValue(String.valueOf(random.nextInt(LEVEL / 2) + LEVEL/2) + "." + rank);// 在随机出的值后+ LEVEL/2，确保不会减出负数
                x = Float.parseFloat(getLeftNumberString().getValue());
                rank = random.nextInt(10);
                getRightNumberString().setValue(String.valueOf(random.nextInt(LEVEL / 2)) + "." + rank);
                y = Float.parseFloat(getRightNumberString().getValue());
            }else {
                rank = random.nextInt(10);
                getLeftNumberString().setValue(String.valueOf(random.nextInt((int)LEVEL_sqrt)) + "." + rank);//将范围设为(int)LEVEL_sqrt，避免最后乘积溢出
                x = Float.parseFloat(getLeftNumberString().getValue());
                rank = random.nextInt(10);
                getRightNumberString().setValue(String.valueOf(random.nextInt((int)LEVEL_sqrt)) + "." + rank);
                y = Float.parseFloat(getRightNumberString().getValue());
            }
        } else {
            if(Operator_num == 0){
                getLeftNumberString().setValue(String.valueOf(random.nextInt(LEVEL/2) + 1));
                //random.nextInt(LEVEL/2) + 1避免出现0
                x = Integer.parseInt(getLeftNumberString().getValue());
                getRightNumberString().setValue(String.valueOf(random.nextInt(LEVEL/2) + 1));
                y = Integer.parseInt(getRightNumberString().getValue());
            }else if(Operator_num == 1){
                getLeftNumberString().setValue(String.valueOf(random.nextInt(LEVEL/2) + 1 + LEVEL/2));
                x = Integer.parseInt(getLeftNumberString().getValue());
                getRightNumberString().setValue(String.valueOf(random.nextInt(LEVEL/2) + 1));
                y = Integer.parseInt(getRightNumberString().getValue());
            }else {
                getLeftNumberString().setValue(String.valueOf(random.nextInt((int) LEVEL_sqrt) + 1));
                x = Integer.parseInt(getLeftNumberString().getValue());
                getRightNumberString().setValue(String.valueOf(random.nextInt((int) LEVEL_sqrt) + 1));
                y = Integer.parseInt(getRightNumberString().getValue());
            }
        }
        switch (Operator[Operator_num]) {//根据运算符保存操作数与结果
            case '+':
                float addition = x + y;
                BigDecimal b = new BigDecimal(addition);//创建新的BigDecimal对象
                addition = b.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
                //保证addition最多为一位小数，其他位作四舍五入处理
                getAnswer().setValue(addition);//将结果保存到myviewmodel里
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y);//保存左右操作数
                break;
            case '-':
                float subtraction = x - y;
                b = new BigDecimal(subtraction);
                subtraction = b.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
                getAnswer().setValue(subtraction);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y);
                break;
            case '×':
                float multiplier = x * y;
                b = new BigDecimal(multiplier);
                multiplier = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                //乘积最多为两位小数
                getAnswer().setValue(multiplier);
                getLeftNumber().setValue(x);
                getRightNumber().setValue(y);
                break;
            case '÷'://整数除法要求整除
                multiplier = x * y;
                getAnswer().setValue(x);//将x作为结果
                getLeftNumber().setValue(multiplier);
                if(getDataType().getValue() == 0){
                    getLeftNumberString().setValue(String.valueOf((int) multiplier));
                    //整数时直接设定左操作数
                }else {
                    b = new BigDecimal(multiplier);
                    multiplier = b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
                    getLeftNumberString().setValue(String.valueOf(multiplier));
                    //小数时确保左操作数最多只有两位小数
                }
                getRightNumber().setValue(y);//y为右操作数
                break;
        }
    }

    public void save_mistake(){  //  用sharedpreferences的方式永久保存错题
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        String str = shp.getString(KEY_MISTAKES,"");  //  获取之前保存的错题
        editor.putString(KEY_MISTAKES,getMistakes().getValue()+str);  //  与新加的错题合成新的字符串
        editor.apply();  //  进行保存
        getMistakes().setValue("");
    }

    public boolean is_empty_mistake(){
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME,Context.MODE_PRIVATE);
        if (shp.getString(KEY_MISTAKES, String.valueOf(1)).equals("")){  //  判断当前的错题集中是否为空
            return true;
        }
        return false;
    }

    public String load_mistake(){  //  从sharedpreferences中读取错题
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME,Context.MODE_PRIVATE);
        return shp.getString(KEY_MISTAKES,"");
    }

    public void save_account(){
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putString(KEY_USERNAME,getUsername().getValue());
        editor.putString(KEY_PASSWORD,getPassword().getValue());
        editor.apply();
    }

    public void load_account(){
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME,Context.MODE_PRIVATE);
        getUsername().setValue(shp.getString(KEY_USERNAME,""));
        getPassword().setValue(shp.getString(KEY_PASSWORD,""));
    }

    @SuppressWarnings("ConstantConditions")
    void save(){  //  用sharedpreferences永久保存做题次数
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(KEY_FINISHED_TIMES,getFinishedTimes().getValue());
        editor.putInt(KEY_TOTAL_NUMBER,shp.getInt(KEY_TOTAL_NUMBER,0)+getTotalQuestionNumber().getValue());
        editor.putInt(KEY_TOTAL_MISTAKES,getTotalMistakes().getValue());
        editor.apply();
        getFinishedQuestionNumber().setValue(0);
    }
    public int load_total_number(){
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME,Context.MODE_PRIVATE);
        return shp.getInt(KEY_TOTAL_NUMBER, 0);
    }

    public int load_total_mistake(){
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME,Context.MODE_PRIVATE);
        return shp.getInt(KEY_TOTAL_MISTAKES,0);
    }

    public void clear(){//清空历史记录
        String str="";
        SharedPreferences shp = getApplication().getSharedPreferences(SAVE_SHP_DATA_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(KEY_FINISHED_TIMES,0);
        getFinishedTimes().setValue(0);  //  完成次数设为0
        getTotalMistakes().setValue(0);
        getFinishedQuestionNumber().setValue(0);
        editor.clear();  //  清空历史错题记录
        editor.apply();
        editor.putString(KEY_MISTAKES,"");
        editor.apply();
    }
    //对回答结果进行处理
    @SuppressWarnings("ConstantConditions")
    void answerCorrect() { // 答对问题时更新题目数，若未生成足够题目，生成新题
        if(getCurrentQuestionNumber().getValue() == 1){
            getCurrentMistakes().setValue(0);
        }
        getFinishedQuestionNumber().setValue(getFinishedQuestionNumber().getValue() + 1);
        if (getFinishedQuestionNumber().getValue() < getTotalQuestionNumber().getValue()) {
            getCurrentQuestionNumber().setValue(getCurrentQuestionNumber().getValue() + 1);
            generator(); // 生成一道新题
        }else {
            getFinishedTimes().setValue(getFinishedTimes().getValue() + 1);
        }
    }

    @SuppressWarnings("ConstantConditions")
    void answerFalse() { // 答错问题时更新题目数，错误题目数，若未生成足够题目，生成新题
        if(getCurrentQuestionNumber().getValue() == 1){
            getCurrentMistakes().setValue(0);
        }
        getTotalMistakes().setValue(getTotalMistakes().getValue()+1);
        getFinishedQuestionNumber().setValue(getFinishedQuestionNumber().getValue() + 1);
        getCurrentMistakes().setValue(getCurrentMistakes().getValue() + 1);//更新错题数
        if (getFinishedQuestionNumber().getValue() < getTotalQuestionNumber().getValue()) {
            getCurrentQuestionNumber().setValue(getCurrentQuestionNumber().getValue() + 1);
            generator(); // 生成一道新题
        }else {
            getFinishedTimes().setValue(getFinishedTimes().getValue() + 1);
        }
    }
}
