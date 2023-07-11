import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:team7_work/FirstTab.dart';
import 'package:team7_work/SecondTap.dart';
import 'package:team7_work/ThirdTab.dart';
import 'package:team7_work/FirstTab.dart';
import 'package:team7_work/FourthTab.dart';
import 'package:team7_work/FifthTab.dart';

class FirstTab extends StatelessWidget {
  FirstTab({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black, // 배경색을 검은색으로 설정
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Container(
          decoration: BoxDecoration(color: Colors.black), // 내용 영역을 흰색으로 설정
          child: ListView(
            children: [
              SizedBox(
                height: 100,
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Spacer(),
                  Column(
                    children: [
                      Text(
                        'Introduce Our Team!',
                        style: TextStyle(
                            color: Colors.white,
                            fontSize: 40,
                            fontWeight: FontWeight.bold),
                      ),
                      Text(
                        'This is ME',
                        style: TextStyle(
                            color: Color.fromARGB(255, 165, 164, 164),
                            fontSize: 25),
                      ),
                    ],
                  ),
                ],
              ),
              SizedBox(
                height: 120,
              ),
              Container(
                // 첫 번째 타일
                decoration: BoxDecoration(
                    color: Color.fromARGB(255, 67, 83, 99),
                    borderRadius: BorderRadius.circular(20)),
                child: Row(
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Icon(
                        Icons.face_sharp,
                        size: 80,
                        color: Colors.white,
                      ),
                    ),
                    SizedBox(
                      width: 20,
                    ),
                    InkWell(
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => SecondTab(),
                          ),
                        );
                      },
                      child: Text(
                        "Ji Gyeon",
                        style: TextStyle(
                            color: Colors.black87,
                            fontSize: 30,
                            fontWeight: FontWeight.w400),
                      ),
                    )
                  ],
                ),
              ),
              Transform.translate(
                // 두 번째 타일
                offset: Offset(0, -20),
                child: Container(
                  decoration: BoxDecoration(
                      color: Color.fromARGB(255, 243, 245, 247),
                      borderRadius: BorderRadius.circular(20)),
                  child: Row(
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Icon(
                          Icons.face_6,
                          size: 80,
                          color: Colors.black,
                        ),
                      ),
                      SizedBox(
                        width: 20,
                      ),
                      InkWell(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => ThirdTab(),
                            ),
                          );
                        },
                        child: Text(
                          'Hyun Jun',
                          style: TextStyle(
                              color: Colors.black87,
                              fontSize: 30,
                              fontWeight: FontWeight.w400),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              Transform.translate(
                // 세 번째 타일
                offset: Offset(0, -40),
                child: Container(
                  decoration: BoxDecoration(
                      color: Color.fromARGB(255, 45, 90, 133),
                      borderRadius: BorderRadius.circular(20)),
                  child: Row(
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Icon(
                          Icons.face_3,
                          size: 80,
                          color: Colors.white,
                        ),
                      ),
                      SizedBox(
                        width: 20,
                      ),
                      InkWell(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => FourthTab(),
                            ),
                          );
                        },
                        child: Text(
                          'Min Ji ',
                          style: TextStyle(
                              color: Colors.black87,
                              fontSize: 30,
                              fontWeight: FontWeight.w400),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              Transform.translate(
                // 네 번째 타일
                offset: Offset(0, -60),
                child: Container(
                  decoration: BoxDecoration(
                      color: Color.fromARGB(255, 169, 170, 136),
                      borderRadius: BorderRadius.circular(20)),
                  child: Row(
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Icon(
                          Icons.face_5,
                          size: 80,
                          color: const Color.fromARGB(255, 61, 45, 45),
                        ),
                      ),
                      SizedBox(
                        width: 20,
                      ),
                      InkWell(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(builder: (context) => FifthTab()),
                          );
                        },
                        child: Text(
                          'Min Jun',
                          style: TextStyle(
                              color: Color.fromARGB(255, 21, 21, 22),
                              fontSize: 30,
                              fontWeight: FontWeight.w400),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
