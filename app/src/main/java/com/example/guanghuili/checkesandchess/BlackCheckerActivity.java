package com.example.guanghuili.checkesandchess;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.guanghuili.checkesandchess.Checkers.BlackChecker;
import com.example.guanghuili.checkesandchess.Checkers.Checker;
import com.example.guanghuili.checkesandchess.Checkers.NullChecker;
import com.example.guanghuili.checkesandchess.Checkers.Player;
import com.example.guanghuili.checkesandchess.Checkers.RedChecker;
import com.example.guanghuili.checkesandchess.Checkers.Room;
import com.example.guanghuili.checkesandchess.Checkers.RoomManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BlackCheckerActivity extends AppCompatActivity {

    private ImageButton[][] imageButtonList;
    //private Checker[][] checkerList;
    private List<List<Checker>> checkerList;//from database
    private ArrayList<int[]> possibleMove;
    private ArrayList<int[]> killList;
    private int [] killLocation;
    private boolean turn;
    private boolean secondClick = false;
    private boolean destroyed = false;
    private int row;
    private int column;
    private boolean disableAllButOneButton = false;
    private NullChecker nullc = new NullChecker();

    private Boolean player2Enter = false;
    private Boolean player2Left = false;
    private Boolean backPressed = false;
    private Boolean paused = false;
    private Boolean waitingMessage = false;

    private FirebaseDatabase database;
    private DatabaseReference refSignUpPlayers;
    private DatabaseReference refRoom;
    private static DatabaseReference refThisRoom;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;

    private RoomManager roomManager = new RoomManager();
    private Player player;
    private Room room;

    private TextView tvRoom;
    private TextView tvPlayer1Name;
    private TextView tvPlayer2Name;

    //row 1
    private ImageButton ibtn_0_0;
    private ImageButton ibtn_0_1;
    private ImageButton ibtn_0_2;
    private ImageButton ibtn_0_3;
    private ImageButton ibtn_0_4;
    private ImageButton ibtn_0_5;
    private ImageButton ibtn_0_6;
    private ImageButton ibtn_0_7;
    //row 2
    private ImageButton ibtn_1_0;
    private ImageButton ibtn_1_1;
    private ImageButton ibtn_1_2;
    private ImageButton ibtn_1_3;
    private ImageButton ibtn_1_4;
    private ImageButton ibtn_1_5;
    private ImageButton ibtn_1_6;
    private ImageButton ibtn_1_7;
    //row 3
    private ImageButton ibtn_2_0;
    private ImageButton ibtn_2_1;
    private ImageButton ibtn_2_2;
    private ImageButton ibtn_2_3;
    private ImageButton ibtn_2_4;
    private ImageButton ibtn_2_5;
    private ImageButton ibtn_2_6;
    private ImageButton ibtn_2_7;
    //row 4
    private ImageButton ibtn_3_0;
    private ImageButton ibtn_3_1;
    private ImageButton ibtn_3_2;
    private ImageButton ibtn_3_3;
    private ImageButton ibtn_3_4;
    private ImageButton ibtn_3_5;
    private ImageButton ibtn_3_6;
    private ImageButton ibtn_3_7;

    //row 5
    private ImageButton ibtn_4_0;
    private ImageButton ibtn_4_1;
    private ImageButton ibtn_4_2;
    private ImageButton ibtn_4_3;
    private ImageButton ibtn_4_4;
    private ImageButton ibtn_4_5;
    private ImageButton ibtn_4_6;
    private ImageButton ibtn_4_7;

    //row 6
    private ImageButton ibtn_5_0;
    private ImageButton ibtn_5_1;
    private ImageButton ibtn_5_2;
    private ImageButton ibtn_5_3;
    private ImageButton ibtn_5_4;
    private ImageButton ibtn_5_5;
    private ImageButton ibtn_5_6;
    private ImageButton ibtn_5_7;

    //row 7
    private ImageButton ibtn_6_0;
    private ImageButton ibtn_6_1;
    private ImageButton ibtn_6_2;
    private ImageButton ibtn_6_3;
    private ImageButton ibtn_6_4;
    private ImageButton ibtn_6_5;
    private ImageButton ibtn_6_6;
    private ImageButton ibtn_6_7;

    //row 8
    private ImageButton ibtn_7_0;
    private ImageButton ibtn_7_1;
    private ImageButton ibtn_7_2;
    private ImageButton ibtn_7_3;
    private ImageButton ibtn_7_4;
    private ImageButton ibtn_7_5;
    private ImageButton ibtn_7_6;
    private ImageButton ibtn_7_7;

    private Button btnSurrender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_checker);

        btnSurrender = findViewById(R.id.btnSurrenderID);
        tvRoom = findViewById(R.id.tvRoomIdID);
        tvPlayer1Name = findViewById(R.id.tvPlayer1ID);
        tvPlayer2Name = findViewById(R.id.tvPlayer2ID);

//row 1
        ibtn_0_0 = findViewById(R.id.ibtn_0_0);
        ibtn_0_1 = findViewById(R.id.ibtn_0_1);
        ibtn_0_2 = findViewById(R.id.ibtn_0_2);
        ibtn_0_3 = findViewById(R.id.ibtn_0_3);
        ibtn_0_4 = findViewById(R.id.ibtn_0_4);
        ibtn_0_5 = findViewById(R.id.ibtn_0_5);
        ibtn_0_6 = findViewById(R.id.ibtn_0_6);
        ibtn_0_7 = findViewById(R.id.ibtn_0_7);


//row 2
        ibtn_1_0 = findViewById(R.id.ibtn_1_0);
        ibtn_1_1 = findViewById(R.id.ibtn_1_1);
        ibtn_1_2 = findViewById(R.id.ibtn_1_2);
        ibtn_1_3 = findViewById(R.id.ibtn_1_3);
        ibtn_1_4 = findViewById(R.id.ibtn_1_4);
        ibtn_1_5 = findViewById(R.id.ibtn_1_5);
        ibtn_1_6 = findViewById(R.id.ibtn_1_6);
        ibtn_1_7 = findViewById(R.id.ibtn_1_7);
//row 3
        ibtn_2_0 = findViewById(R.id.ibtn_2_0);
        ibtn_2_1 = findViewById(R.id.ibtn_2_1);
        ibtn_2_2 = findViewById(R.id.ibtn_2_2);
        ibtn_2_3 = findViewById(R.id.ibtn_2_3);
        ibtn_2_4 = findViewById(R.id.ibtn_2_4);
        ibtn_2_5 = findViewById(R.id.ibtn_2_5);
        ibtn_2_6 = findViewById(R.id.ibtn_2_6);
        ibtn_2_7 = findViewById(R.id.ibtn_2_7);
//row 4
        ibtn_3_0 = findViewById(R.id.ibtn_3_0);
        ibtn_3_1 = findViewById(R.id.ibtn_3_1);
        ibtn_3_2 = findViewById(R.id.ibtn_3_2);
        ibtn_3_3 = findViewById(R.id.ibtn_3_3);
        ibtn_3_4 = findViewById(R.id.ibtn_3_4);
        ibtn_3_5 = findViewById(R.id.ibtn_3_5);
        ibtn_3_6 = findViewById(R.id.ibtn_3_6);
        ibtn_3_7 = findViewById(R.id.ibtn_3_7);

//row 5
        ibtn_4_0 = findViewById(R.id.ibtn_4_0);
        ibtn_4_1 = findViewById(R.id.ibtn_4_1);
        ibtn_4_2 = findViewById(R.id.ibtn_4_2);
        ibtn_4_3 = findViewById(R.id.ibtn_4_3);
        ibtn_4_4 = findViewById(R.id.ibtn_4_4);
        ibtn_4_5 = findViewById(R.id.ibtn_4_5);
        ibtn_4_6 = findViewById(R.id.ibtn_4_6);
        ibtn_4_7 = findViewById(R.id.ibtn_4_7);

//row 6
        ibtn_5_0 = findViewById(R.id.ibtn_5_0);
        ibtn_5_1 = findViewById(R.id.ibtn_5_1);
        ibtn_5_2 = findViewById(R.id.ibtn_5_2);
        ibtn_5_3 = findViewById(R.id.ibtn_5_3);
        ibtn_5_4 = findViewById(R.id.ibtn_5_4);
        ibtn_5_5 = findViewById(R.id.ibtn_5_5);
        ibtn_5_6 = findViewById(R.id.ibtn_5_6);
        ibtn_5_7 = findViewById(R.id.ibtn_5_7);

//row 7
        ibtn_6_0 = findViewById(R.id.ibtn_6_0);
        ibtn_6_1 = findViewById(R.id.ibtn_6_1);
        ibtn_6_2 = findViewById(R.id.ibtn_6_2);
        ibtn_6_3 = findViewById(R.id.ibtn_6_3);
        ibtn_6_4 = findViewById(R.id.ibtn_6_4);
        ibtn_6_5 = findViewById(R.id.ibtn_6_5);
        ibtn_6_6 = findViewById(R.id.ibtn_6_6);
        ibtn_6_7 = findViewById(R.id.ibtn_6_7);

//row 7
        ibtn_7_0 = findViewById(R.id.ibtn_7_0);
        ibtn_7_1 = findViewById(R.id.ibtn_7_1);
        ibtn_7_2 = findViewById(R.id.ibtn_7_2);
        ibtn_7_3 = findViewById(R.id.ibtn_7_3);
        ibtn_7_4 = findViewById(R.id.ibtn_7_4);
        ibtn_7_5 = findViewById(R.id.ibtn_7_5);
        ibtn_7_6 = findViewById(R.id.ibtn_7_6);
        ibtn_7_7 = findViewById(R.id.ibtn_7_7);

        imageButtonList = new ImageButton[][]
                {{null, ibtn_0_1, null, ibtn_0_3, null, ibtn_0_5, null, ibtn_0_7},
                        {ibtn_1_0, null, ibtn_1_2, null, ibtn_1_4, null, ibtn_1_6, null},
                        {null, ibtn_2_1, null, ibtn_2_3, null, ibtn_2_5, null, ibtn_2_7},
                        {ibtn_3_0, null, ibtn_3_2, null, ibtn_3_4, null, ibtn_3_6, null},
                        {null, ibtn_4_1, null, ibtn_4_3, null, ibtn_4_5, null, ibtn_4_7},
                        {ibtn_5_0, null, ibtn_5_2, null, ibtn_5_4, null, ibtn_5_6, null},
                        {null, ibtn_6_1, null, ibtn_6_3, null, ibtn_6_5, null, ibtn_6_7},
                        {ibtn_7_0, null, ibtn_7_2, null, ibtn_7_4, null, ibtn_7_6, null}};

        room = (Room)getIntent().getSerializableExtra("room");
        tvRoom.setText(getResources().getText(R.string.room_id) + String.valueOf(room.getId()));

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        refSignUpPlayers = database.getReference("Signed Up Players");
        refRoom = database.getReference("Room");
        refThisRoom = refRoom.child("available").child(String.valueOf(room.getId()));

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = mAuth.getCurrentUser();
            }
        });

        refSignUpPlayers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = mAuth.getCurrentUser();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if(dataSnapshot1.getValue(Player.class).getUsername().equals(user.getDisplayName())){
                        player = dataSnapshot1.getValue(Player.class);
                        tvPlayer1Name.setText(getResources().getText(R.string.player1) + player.getUsername());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        refThisRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(backPressed == false) {
                    if(paused == false) {
                        if (player2Left == false) {
                            if(dataSnapshot.getValue() != null) {
                                if (dataSnapshot.getValue(Room.class).getPlayer2() != null) {
                                    tvPlayer2Name.setText(getResources().getText(R.string.player2) + dataSnapshot.getValue(Room.class).getPlayer2().getUsername());
                                    turn = dataSnapshot.getValue(Room.class).getTurn();
                                    checkerList = dataSnapshot.getValue(Room.class).getCheckerList();
                                    processCheckerList();
                                    if (player2Enter == false) {
                                        Toast.makeText(BlackCheckerActivity.this, "Player Entered", Toast.LENGTH_LONG).show();
                                        player2Enter = true;
                                        refThisRoom.removeEventListener(this);//when change the ref path, the listener won't update, so remove it and add it back
                                        refThisRoom = refRoom.child("unavailable").child(String.valueOf(room.getId()));
                                        refThisRoom.addValueEventListener(this);
                                    }
                                    updateAllButtons();
                                    disableButtons();
                                } else {//player2 is null
                                    if (waitingMessage == false) {
                                        Toast.makeText(BlackCheckerActivity.this, "Waiting for another player", Toast.LENGTH_LONG).show();
                                        waitingMessage = true;
                                    } else {
                                        player2Left = true;
                                        Toast.makeText(BlackCheckerActivity.this, "User exited", Toast.LENGTH_LONG).show();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(BlackCheckerActivity.this);
                                        builder.setTitle("Room Update");
                                        builder.setMessage("Player left the room! You win");
                                        builder.setCancelable(false);
                                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                player.updateWin();
                                                refSignUpPlayers.child(player.getUsername()).setValue(player);
                                                BlackCheckerActivity.super.onBackPressed();
                                                finish();
                                            }
                                        });
                                        builder.show();
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("pausedCalled", "called");
        if(backPressed == false) {
            if(player2Left == false) {
                paused = true;
                refThisRoom.child("player1").removeValue();
                player.updateLoss();
                refSignUpPlayers.child(player.getUsername()).setValue(player);
                finish();
            }
        }
    }

    public void myOnClick(View view) {
        if (turn) {//BlackChecker's turn
            if (secondClick == false) {//(first click)
                for (int r = 0; r < imageButtonList.length; r++) {
                    for (int c = 0; c < imageButtonList[r].length; c++) {
                        if (imageButtonList[r][c] != null) {//if imageButtonList[r][c] is not null
                            if (view.getId() == imageButtonList[r][c].getId()) {//get the r and c of the ibtn clicked
                                if (!(checkerList.get(r).get(c) instanceof NullChecker)) {//if the corresponding location in the checkerList has a checker
                                    if (checkerList.get(r).get(c) instanceof BlackChecker) {//if clicked checker is a blackChecker
                                        row = r;
                                        column = c;
                                        Log.d("NoteSaveRow", String.valueOf(row));
                                        Log.d("NoteSaveColumn", String.valueOf(column));
                                        if (destroyed == false) {
                                            possibleMove = checkerList.get(r).get(c).getMove(checkerList);//get the possibleMove from BlackChecker class
                                            killList = checkerList.get(r).get(c).getKillList();//get the corresponding killList
                                            if (disableAllButOneButton == false) {
                                                updateAllButtons();
                                                disableButtons();
                                            }
                                            for (int i = 0; i < possibleMove.size(); i++) {//go through the possibleMove
                                                int row = possibleMove.get(i)[0];//get each row
                                                int column = possibleMove.get(i)[1];//get each column
                                                imageButtonList[row][column].setClickable(true);//make the possible places clickable
                                                imageButtonList[row][column].setBackgroundColor(Color.WHITE);
                                            }
                                            secondClick = true;
                                        } else {
                                            destroyed = false;
                                            possibleMove = checkerList.get(r).get(c).getMove2(checkerList);//get the possibleMove from BlackChecker class
                                            killList = checkerList.get(r).get(c).getKillList();
                                            if (possibleMove.size() != 0) {
                                                for (int i = 0; i < possibleMove.size(); i++) {//go through the possibleMove
                                                    int row = possibleMove.get(i)[0];//get each row
                                                    int column = possibleMove.get(i)[1];//get each column
                                                    disableAllButPossible(possibleMove);
                                                }
                                                secondClick = true;
                                            } else {
                                                secondClick = false;
                                                turn = false;
                                                updateAllButtons();
                                                disableAllButOneButton(r, c);
                                                disableAllButOneButton = true;
                                                refThisRoom.child("checkerList").setValue(checkerList);
                                                refThisRoom.child("turn").setValue(turn);
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            } else {//BlackChecker's turn (Second Click)
                for (int r = 0; r < imageButtonList.length; r++) {
                    for (int c = 0; c < imageButtonList[r].length; c++) {
                        if (imageButtonList[r][c] != null) {
                            if (!(checkerList.get(r).get(c) instanceof RedChecker)) {
                                if (view.getId() == imageButtonList[r][c].getId()) {
                                    if (checkerList.get(r).get(c) instanceof NullChecker) {
                                        checkerList.get(r).set(c,new BlackChecker(checkerList.get(row).get(column)));//row and column are the position of the new position, copy the checker to the new position
                                        checkerList.get(r).get(c).setRow(r);
                                        checkerList.get(r).get(c).setColumn(c);
                                        checkerList.get(r).get(c).setType("BlackChecker");
                                        if (checkerList.get(r).get(c).getRow() == 0) {
                                            checkerList.get(r).get(c).setCrownStatus(true);
                                        }
                                        checkerList.get(row).set(column, nullc);//delete the checker in the old location
                                        killLocation = getKillCheckerLocation(r, c);
                                        if (killLocation != null) {
                                            checkerList.get(killLocation[0]).set(killLocation[1],nullc);
                                            destroyed = true;
                                            secondClick = false;
                                            updateAllButtons();
                                            possibleMove = checkerList.get(r).get(c).getMove2(checkerList);
                                            if (possibleMove.size() == 0) {
                                                disableAllButOneButton = false;
                                                destroyed = false;
                                                turn = false;
                                                disableButtons();
                                                refThisRoom.child("checkerList").setValue(checkerList);
                                                refThisRoom.child("turn").setValue(turn);
                                            } else {
                                                turn = true;
                                                disableAllButOneButton(r, c);
                                                disableAllButOneButton = true;
                                            }
                                        } else {
                                            disableAllButOneButton = false;
                                            destroyed = false;
                                            secondClick = false;
                                            turn = false;
                                            updateAllButtons();
                                            disableButtons();
                                            refThisRoom.child("checkerList").setValue(checkerList);
                                            refThisRoom.child("turn").setValue(turn);
                                        }
                                        break;
                                    } else {//**for the user who wants to change his/her moves
                                        updateAllButtons();//update the layout and made all the buttons clickable
                                        disableButtons();
                                        secondClick = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void disableButtons(){//disable unmovable checkers
        Log.d("DisableCalled","Turn " + String.valueOf(turn));
        if(turn) {//if it is black checkers turn
            for (int r = 0; r < checkerList.size(); r++) {
                for (int c = 0; c < checkerList.get(r).size(); c++) {
                    if (checkerList.get(r).get(c) instanceof RedChecker) {//if the checker is red (disable all the redCheckers)
                        imageButtonList[r][c].setClickable(false);
                        //imageButtonList[r][c].setBackgroundColor(Color.BLACK);
                    }
                    else if(checkerList.get(r).get(c) instanceof BlackChecker){//disable the unmovable blackCheckers
                        if(r == 0){//if the blackChecker is at row 0 (crown)
                            imageButtonList[r][c].setClickable(true);
                        }
                        else {
                            if (c == 0){
                                if(checkerList.get(r - 1).get(c + 1) instanceof BlackChecker) { //if the blackChecker is at column 0 and there is one blackChecker at upper right
                                    imageButtonList[r][c].setClickable(false);
                                    //imageButtonList[r][c].setBackgroundColor(Color.BLACK);
                                }
                            }
                            else if (c == 7){
                                if(checkerList.get(r - 1).get(c - 1) instanceof BlackChecker) {//if the blackChecker is at column 7 and there is one blackChecker at upper left
                                    imageButtonList[r][c].setClickable(false);
                                    //imageButtonList[r][c].setBackgroundColor(Color.BLACK);
                                }
                            } else {//if the blackChecker is located at column 1 - 6
                                if (checkerList.get(r - 1).get(c - 1) instanceof BlackChecker && checkerList.get(r - 1).get(c + 1) instanceof BlackChecker) {//if there are blackCheckers on both upper left and right
                                    imageButtonList[r][c].setClickable(false);
                                    //imageButtonList[r][c].setBackgroundColor(Color.BLACK);
                                }
                            }
                        }
                    }
                    else{//if the checkerList updates ex: one black checker died, need to update the corresponding image button to not clickable
                        if(imageButtonList[r][c] != null) {
                            imageButtonList[r][c].setClickable(false);
                            //imageButtonList[r][c].setBackgroundColor(Color.BLACK);
                        }
                    }
                }
            }
        }
        else{//if it is red checkers turn
            for (int r = 0; r < checkerList.size(); r++) {
                for (int c = 0; c < checkerList.get(r).size(); c++) {
                    if (checkerList.get(r).get(c) instanceof BlackChecker) {//if the checker is black (disable all the blackCheckers)
                        imageButtonList[r][c].setClickable(false);
                        //imageButtonList[r][c].setBackgroundColor(Color.BLACK);
                    }
                    else if(checkerList.get(r).get(c) instanceof RedChecker) {//disable the unmovable red Checkers
                        if (r == 7) {//if the red checker is at row 7 (crown)
                            imageButtonList[r][c].setClickable(true);
                        } else {
                            if (c == 0) {
                                if (checkerList.get(r + 1).get(c + 1) instanceof RedChecker) { //if the red Checker is at column 0 and there is one redChecker at upper right
                                    imageButtonList[r][c].setClickable(false);
                                    //imageButtonList[r][c].setBackgroundColor(Color.BLACK);
                                }
                            } else if (c == 7) {
                                if (checkerList.get(r + 1).get(c - 1) instanceof RedChecker) {//if the blackChecker is at column 7 and there is one blackChecker at upper left
                                    imageButtonList[r][c].setClickable(false);
                                    //imageButtonList[r][c].setBackgroundColor(Color.BLACK);
                                }
                            } else {//if the blackChecker is located at column 1 - 6
                                if (checkerList.get(r + 1).get(c - 1) instanceof RedChecker && checkerList.get(r + 1).get(c + 1) instanceof RedChecker) {//if there are redCheckers on both upper left and right
                                    imageButtonList[r][c].setClickable(false);
                                    //imageButtonList[r][c].setBackgroundColor(Color.BLACK);
                                }
                            }
                        }

                    }
                    else{//if the checkerList updates ex: one red checker died, need to update the corresponding image button to not clickable
                        if(imageButtonList[r][c] != null) {
                            imageButtonList[r][c].setClickable(false);
                            //imageButtonList[r][c].setBackgroundColor(Color.BLACK);
                        }
                    }
                }
            }
        }

    }

    public void disableAllButOneButton(int row, int column){
        updateAllButtons();
        Log.d("DisableAllButOneButton","called");
        for (int r = 0; r < imageButtonList.length; r++) {
            for (int c = 0; c < imageButtonList[r].length; c++) {
                if(imageButtonList[r][c] != null) {
                    imageButtonList[r][c].setClickable(false);
                }
            }
        }
        imageButtonList[row][column].setClickable(true);
    }

    public void disableAllButPossible(ArrayList <int[]> possiblemove){
        updateAllButtons();
        for (int r = 0; r < imageButtonList.length; r++) {
            for (int c = 0; c < imageButtonList[r].length; c++) {
                if(imageButtonList[r][c] != null) {
                    imageButtonList[r][c].setClickable(false);
                }
            }
        }
        for (int i = 0; i < possibleMove.size(); i++) {//go through the possibleMove
            int row = possibleMove.get(i)[0];//get each row
            int column = possibleMove.get(i)[1];//get each column
            imageButtonList[row][column].setClickable(true);//make the possible places clickable
            imageButtonList[row][column].setBackgroundColor(Color.WHITE);
        }


    }

    public void updateAllButtons(){//update the whole layout based on the contents in checkerList, also make all the imageButtons clickable
        Log.d("UpdateCalled","Turn " + String.valueOf(turn));
        for(int r = 0; r < imageButtonList.length; r++) {
            for (int c = 0; c < imageButtonList[r].length; c++) {
                if (imageButtonList[r][c] != null) {//if the imageButton is not null
                    imageButtonList[r][c].setClickable(true);//make all the imageButtons clickable
                    imageButtonList[r][c].setImageDrawable(null);//Erase all the drawables and background color

                    if (! (checkerList.get(r).get(c) instanceof NullChecker)) {//in the movable location if the checker in the checkerList is not null
                        if (checkerList.get(r).get(c) instanceof BlackChecker) {//if its a BlackChecker
                            if (checkerList.get(r).get(c).isCrownStatus() == false) {
                                Log.d("UpdateCalled","Turn " + String.valueOf(turn));
                                imageButtonList[r][c].setImageResource(R.drawable.black_dot);//change the image to black dot
                            } else {
                                imageButtonList[r][c].setImageResource(R.drawable.black_crown);
                            }
                        }
                        if (checkerList.get(r).get(c) instanceof RedChecker) {//if its a RedChecker
                            if (checkerList.get(r).get(c).isCrownStatus() == false) {
                                imageButtonList[r][c].setImageResource(R.drawable.red_dot);//change the image to red dot
                            } else {
                                imageButtonList[r][c].setImageResource(R.drawable.red_crown);
                            }
                        }
                    }
                    imageButtonList[r][c].setBackgroundColor(Color.parseColor("#c9af98"));//set the desirable background color
                }
            }
        }
    }

    public int [] getKillCheckerLocation(int rIndex, int cIndex){
        int [] location = new int [2];
        for (int i = 0; i < possibleMove.size(); i++){
            if(possibleMove.get(i)[0] == rIndex && possibleMove.get(i)[1] == cIndex){
                location = killList.get(i);
                break;
            }
        }
        return location;
    }

    public void processCheckerList() {
        Boolean status;
        String type;
        for (int r = 0; r < checkerList.size(); r++) {
            for (int c = 0; c < checkerList.get(r).size(); c++) {
                if (checkerList.get(r).get(c).getType().equals("BlackChecker")) {//if the checker is black
                    type = checkerList.get(r).get(c).getType();
                    status = checkerList.get(r).get(c).isCrownStatus();
                    checkerList.get(r).set(c, (new BlackChecker(checkerList.get(r).get(c).getRow(), checkerList.get(r).get(c).getColumn())));
                    checkerList.get(r).get(c).setCrownStatus(status);
                    checkerList.get(r).get(c).setType(type);
                }
                if (checkerList.get(r).get(c).getType().equals("RedChecker")) {//if the checker is red
                    type = checkerList.get(r).get(c).getType();
                    status = checkerList.get(r).get(c).isCrownStatus();
                    checkerList.get(r).set(c, (new RedChecker(checkerList.get(r).get(c).getRow(), checkerList.get(r).get(c).getColumn())));
                    checkerList.get(r).get(c).setCrownStatus(status);
                    checkerList.get(r).get(c).setType(type);
                }
                if (checkerList.get(r).get(c).getType().equals("NullChecker")) {//if the checker is red
                    type = checkerList.get(r).get(c).getType();
                    checkerList.get(r).set(c, (new NullChecker()));
                    checkerList.get(r).get(c).setType(type);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Log.d("Pressed","Hello");
        if(player2Enter == true) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Leaving while in a game");
            builder.setMessage("Leave now means surrender");
            builder.setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    backPressed = true;
                    refThisRoom.child("player1").removeValue();
                    player.updateLoss();
                    refSignUpPlayers.child(player.getUsername()).setValue(player);
                    BlackCheckerActivity.super.onBackPressed();
                    finish();
                }
            });
            builder.setNegativeButton("Stay", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
        else{
            backPressed = true;
            refThisRoom.removeValue();
            BlackCheckerActivity.super.onBackPressed();
            finish();
        }
    }
}
