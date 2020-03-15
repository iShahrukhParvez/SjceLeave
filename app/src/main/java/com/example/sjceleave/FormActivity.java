package com.example.sjceleave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.Button;
//import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
//import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import java.util.Date;

public class FormActivity extends AppCompatActivity {
EditText name, rollno, leavesofar, leavet, mlet, plet, absentet, totdayset, reasonet;
Spinner ugpg, course, year, branch, section;
String nameString;
int leavecounter;
long temp;
EditText enterdate,fromdate,todate;
Button submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Intent j = getIntent();
      //  final String email = j.getStringExtra("email");
        name = findViewById(R.id.name);
        rollno = findViewById(R.id.rollno);
        leavesofar = findViewById(R.id.leavesofar);
        leavet = findViewById(R.id.leaveet);
        mlet= findViewById(R.id.medicalleaveet);
        plet = findViewById(R.id.prileaveet);
        absentet = findViewById(R.id.absentet);
        totdayset = findViewById(R.id.totdayset);
        reasonet = findViewById(R.id.reasonet);

        ugpg= findViewById(R.id.ugpg);
        course = findViewById(R.id.course);
        year = findViewById(R.id.year);
        branch = findViewById(R.id.branch);
        section = findViewById(R.id.section);

        enterdate = findViewById(R.id.enterdate);
        fromdate = findViewById(R.id.fromdate);
        todate = findViewById(R.id.todate);

        submitbtn = findViewById(R.id.submitbtn);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameString = name.getText().toString();

                String rollnoString = rollno.getText().toString();

                String ugpgString = ugpg.getSelectedItem().toString();
                String courseString = course.getSelectedItem().toString();
                String branchString = branch.getSelectedItem().toString();
                String yearString = year.getSelectedItem().toString();
                String sectionString = section.getSelectedItem().toString();

                final String leavesofarString = leavesofar.getText().toString();
                String leavetString = leavet.getText().toString();
                String mletString = mlet.getText().toString();
                String absentetString = absentet.getText().toString();
                String pletString = plet.getText().toString();
                String totdaysetString = totdayset.getText().toString();
                String reasonetString = reasonet.getText().toString();

                String enterdateString = enterdate.getText().toString();
                String fromdateString = fromdate.getText().toString();
                String todateString = todate.getText().toString();
                Log.e("HERE",nameString+rollnoString+ugpgString+courseString+branchString+yearString+sectionString+leavesofarString+leavetString+mletString+absentetString+pletString+totdaysetString+reasonetString+enterdateString+fromdateString+todateString);
                final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                db.child(nameString).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("NumberofLeaves"))
                        {
                            temp = (long)dataSnapshot.child("NumberofLeaves").getValue();
                            leavecounter = (int)temp;
                            db.child(nameString).child("NumberofLeaves").setValue(leavecounter+1);
                        }
                        else
                        {
                            db.child(nameString).child("NumberofLeaves").setValue(1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                db.child(nameString).child("RollNo").setValue(rollnoString);
                db.child(nameString).child("Branch").setValue(branchString);
                db.child(nameString).child("Year").setValue(yearString);
                db.child(nameString).child("Section").setValue(sectionString);
                db.child(nameString).child("Leave So Far").setValue(leavesofarString);
                db.child(nameString).child("Total Days").setValue(totdaysetString);
                db.child(nameString).child("Reason").setValue(reasonetString);
                if (enterdateString.equals("")) {
                    db.child(nameString).child("From Date").setValue(fromdateString);
                    db.child(nameString).child("To Date").setValue(todateString);
                } else {
                    db.child(nameString).child("Date").setValue(enterdateString);
                }
            }
        });



    }
}
