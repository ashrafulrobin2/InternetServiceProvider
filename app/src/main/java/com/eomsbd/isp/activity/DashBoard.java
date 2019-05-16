package com.eomsbd.isp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.eomsbd.isp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class DashBoard extends AppCompatActivity {
    BarChart barChart;
    ArrayList<String> dates;
    Random random;
    ArrayList<BarEntry> barEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        barChart = (BarChart) findViewById(R.id.bargraph);
         setTitle("Dashboard");
        createRandomBarGraph("2018/05/05", "2019/06/01");
    }
    public void createRandomBarGraph(String Date1, String Date2){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date date1 = simpleDateFormat.parse(Date1);
            Date date2 = simpleDateFormat.parse(Date2);

            Calendar mDate1 = Calendar.getInstance();
            Calendar mDate2 = Calendar.getInstance();
            mDate1.clear();
            mDate2.clear();

            mDate1.setTime(date1);
            mDate2.setTime(date2);

            dates = new ArrayList<>();
            dates = getList(mDate1,mDate2);

            barEntries = new ArrayList<>();
            float max = 0f;
            float value = 0f;
            random = new Random();
            for(int j = 0; j< dates.size();j++){
                max = 100f;
                value = random.nextFloat()*max;
                barEntries.add(new BarEntry(value,j));
            }

        }catch(ParseException e){
            e.printStackTrace();
        }

        BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");
        BarData barData = new BarData( dates, barDataSet);
        barChart.setData(barData);
        barChart.setDescription("My First Bar Graph!");

    }

    public ArrayList<String> getList(Calendar startDate, Calendar endDate){
        ArrayList<String> list = new ArrayList<String>();
        while(startDate.compareTo(endDate)<=0){
            list.add(getDate(startDate));
            startDate.add(Calendar.DAY_OF_MONTH,1);
        }
        return list;
    }

    public String getDate(Calendar cld){
        String curDate = cld.get(Calendar.YEAR) + "/" + (cld.get(Calendar.MONTH) + 1) + "/"
                +cld.get(Calendar.DAY_OF_MONTH);
        try{
            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(curDate);
            curDate =  new SimpleDateFormat("yyy/MM/dd").format(date);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return curDate;
    }


        @Override
        public void onBackPressed(){
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

        }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            startActivity(new Intent(DashBoard.this, LoginActivity.class));
            return true;
        }
       /* if (drawerToggle.onOptionsItemSelected(item))
            return true;*/
        return super.onOptionsItemSelected(item);
    }



}
