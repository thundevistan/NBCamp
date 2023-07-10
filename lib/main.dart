import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'dart:convert';
import 'package:team7_work/FirstTab.dart';
import 'package:team7_work/ThirdTab.dart';
import 'package:team7_work/SecondTab.dart';
import 'package:team7_work/FourthTab.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: DefaultTabController(
        length: 5,
        initialIndex: 1,
        child: HomePage(),
      ),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState(); }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(20.0),
            child: Container(
              alignment: Alignment.topCenter,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  GestureDetector(
                    onTap: () => _onTabTapped(context, 0),
                    child: Image.asset(
                      'assets/home.png',
                      width: 30,
                      height: 30,
                    ),
                  ),
                  GestureDetector(
                    onTap: () => _onTabTapped(context, 1),
                    child: Image.asset(
                      'assets/settings.png',
                      width: 30,
                      height: 30,
                    ),
                  ),
                  GestureDetector(
                    onTap: () => _onTabTapped(context, 2),
                    child: Image.asset(
                      'assets/person.png',
                      width: 30,
                      height: 30,
                    ),
                  ),
                  GestureDetector(
                    onTap: () => _onTabTapped(context, 3),
                    child: Image.asset(
                      'assets/work.png',
                      width: 30,
                      height: 30,
                    ),
                  ),
                  GestureDetector(
                    onTap: () => _onTabTapped(context, 4),
                    child: Image.asset(
                      'assets/favorite.png',
                      width: 30,
                      height: 30,
                    ),
                  ),
                ],
              ),
            ),
          ),
          Expanded(
            child: TabBarView(
              children: [
                FirstTab(),
                SecondTab(),
                ThirdTab(),
                FourthTab(),
                FifthTab(),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
