package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.databinding.ActivitySignupBinding;
import com.example.foodapp.model.user;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Activity extends AppCompatActivity {
    ActivitySignupBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
   TextView login1,upload;
    GoogleSignInClient mGoogleSignInClient;
  Button signUp;
    ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth =FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        String googleClientId="402398896800-3n5764svfann1jtfsjtv07kuv3n6pb8a.apps.googleusercontent.com";
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(googleClientId)
                .requestEmail()
                .build();
// Build a GoogleSignInClient with the options specified by gso.

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        getSupportActionBar().hide();
        progressDialog=new ProgressDialog(Signup_Activity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Your Account is being Created");
        upload=findViewById(R.id.textUpload);
        binding.textVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup_Activity.this,otpActivity.class);
                startActivity(intent);
            }
        });
        binding.textUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Signup_Activity.this)
                        .crop()
                        .start();
            }
        });
        login1=findViewById(R.id.TextviewLogin);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Signup_Activity.this,SignInActivity.class);
                startActivity(intent);
            }
        });
        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Signup_Activity.this,"Working",Toast.LENGTH_SHORT).show();
                progressDialog.show();
                auth.createUserWithEmailAndPassword(binding.editTextTextEmailAddress.getText().toString(),binding.editTextNumberPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    user user1=new user(binding.editTextTextPersonName.getText().toString(),binding.editTextTextEmailAddress.getText().toString(),binding.editTextNumberPassword.getText().toString());
                                    String id=task.getResult().getUser().getUid();
                                    database.getReference().child("user").child(id).setValue("user");
                                    Toast.makeText(Signup_Activity.this,"User Created Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(Signup_Activity.this,SignInActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(Signup_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });



            }
        });
        binding.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        if(auth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(Signup_Activity.this,MainPageActivity.class);
            startActivity(intent);

        }
    }
    int RC_SIGN_IN=65;
    private void signIn(){
        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri imguri = data.getData();
        binding.profileImage.setImageURI(imguri);
        if(resultCode != RESULT_CANCELED) {
            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    Log.w("TAG", "Google sign in failed", e);
                }
            }
        }

    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential= GoogleAuthProvider.getCredential(idToken,null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("TAG","signInWithCredential : success");
                            FirebaseUser user=auth.getCurrentUser();
//                            user user2=new user();
//                            user2.setUserId(user2.getUserId());
//                            user2.setUserName(user2.getUserName());
//                            user2.setProfilepicture(user2.getProfilepicture().toString());
//                            database.getReference().child("user").child(user2.getUserId()).setValue(user2);
                            Intent intent=new Intent(Signup_Activity.this,MainPageActivity.class);
                            startActivity(intent);

                            // updateUI(user);
                        }
                        else {
                            Log.w("TAG","signInWithCredential : failure",task.getException());
                            // Snackbar.make(mBinding.mainLayout,"Authentication Failed.",Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }


}