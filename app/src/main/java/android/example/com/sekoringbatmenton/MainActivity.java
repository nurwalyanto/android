package android.example.com.sekoringbatmenton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Badminton obj;
    Button p1,p2,ok,cancel;
    TextView t1,t2;
    TextView sa1,sa2,sa3;
    TextView sb1,sb2,sb3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obj=new Badminton();

//        for(int i=0;i<3;i++){
//            sa=new TextView[3];
//            sb=new TextView[3];
//        }
        p1=(Button)findViewById(R.id.poin1);
        t1=(TextView)findViewById(R.id.p1);
        p2=(Button)findViewById(R.id.poin2);
        t2=(TextView)findViewById(R.id.p2);
        ok=(Button)findViewById(R.id.ok);
        cancel=(Button)findViewById(R.id.cancel);
        sa1=(TextView)findViewById(R.id.skorA1);
        sa2=(TextView)findViewById(R.id.skorA2);
        sa3=(TextView)findViewById(R.id.skorA3);
        sb1=(TextView)findViewById(R.id.skorB1);
        sb2=(TextView)findViewById(R.id.skorB2);
        sb3=(TextView)findViewById(R.id.skorB3);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Integer.parseInt(p1.getText().toString())+1
                t2.setText(String.valueOf(0));
                int a=Integer.parseInt(t1.getText().toString());
                a+=1;
                t1.setText(String.valueOf(a));
            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Integer.parseInt(p1.getText().toString())+1
                t1.setText(String.valueOf(0));
                int a=Integer.parseInt(t2.getText().toString());
                a+=1;
                t2.setText(String.valueOf(a));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setText(String.valueOf(0));
                t2.setText(String.valueOf(0));
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obj.tambah(Integer.parseInt(t1.getText().toString()),Integer.parseInt(t2.getText().toString()));
                sa1.setText(String.valueOf(obj.getpA(1)));
                sb1.setText(String.valueOf(obj.getpB(1)));
                sa2.setText(String.valueOf(obj.getpA(2)));
                sb2.setText(String.valueOf(obj.getpB(2)));
                sa3.setText(String.valueOf(obj.getpA(3)));
                sb3.setText(String.valueOf(obj.getpB(3)));
            }
        });
        Thread myThread = null;
        obj=new Badminton();
        Runnable myRunnableThread = new CountDownRunner();
        myThread= new Thread(myRunnableThread);
        myThread.start();

    }
    class Badminton{
        int babak = 1;
        int skorA=0;
        int skorB=0;
        int[] menang=new int[3];
        boolean jus=false;
        int[] pA=new int[3];
        int[] pB=new int[3];
        Badminton(){
            for (int i=0;i<3;i++){
                pA[i]=0;
                pB[i]=0;
                menang[i]=0;
            }
        }
        public void  tambah(int a,int b){
            if(a>b){
                tambahPA(a);
            }else{
                tambahPB(b);
            }
        }
        void tambahPA(int p){
            pA[babak-1]+=p;
            kemenangan();
        }
        void tambahPB(int p){
            pB[babak-1]+=p;
            kemenangan();
        }

        public int getBabak() {
            return babak;
        }

        public int getSkorA() {
            return skorA;
        }

        public int getSkorB() {
            return skorB;
        }

        public int getpA(int b) {
            return pA[b-1];
        }

        public int getpB(int b) {
            return pB[b-1];
        }
        public int kemenangan(int b){
            return menang[b];
        }
        public int kemenangan(){
            if((pA[babak]==20 && pB[babak]==20) ||jus==true){
                jus=true;
                if(pA[babak-1]%pB[babak-1]>1 || pB[babak-1]%pA[babak-1]>1){
                    if(pA[babak-1]>pB[babak-1]){ skorA+=1;menang[babak-1]=1;}else{skorB+=1;menang[babak-1]=2;}
                    babak+=1;
                    jus=false;
                }
                Toast.makeText(getApplicationContext(),"itu",Toast.LENGTH_SHORT);


            }else if(pA[babak-1]>20 || pB[babak-1]>20){
                if(pA[babak-1]>pB[babak-1]){
                    skorA+=1;menang[babak-1]=1;
                }else{
                    skorB+=1;menang[babak-1]=2;
                }
                babak+=1;
                Toast.makeText(getApplicationContext(),"ini",Toast.LENGTH_SHORT);
            }
            return babak;
        }
    }
    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try{
                    TextView txtCurrentTime= (TextView)findViewById(R.id.jam);
                    Date dt = new Date();
                    int hours = dt.getHours();
                    int minutes = dt.getMinutes();
                    int seconds = dt.getSeconds();
                    String curTime = hours + ":" + minutes + ":" + seconds;
                    txtCurrentTime.setText(curTime);
                }catch (Exception e) {}
            }
        });
    }


    class CountDownRunner implements Runnable{
        // @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    doWork();
                    Thread.sleep(1000); // Pause of 1 Second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }catch(Exception e){
                }
            }
        }
    }

}

