import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'dart:convert';
import 'package:team7_work/FirstTab.dart';
import 'package:team7_work/SecondTap.dart';
import 'package:team7_work/ThirdTab.dart';
import 'package:team7_work/FirstTab.dart';
import 'package:team7_work/FourthTab.dart';
import 'package:team7_work/FifthTab.dart';
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
        initialIndex: 0,
        child: HomePage(),
      ),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          TabBarView(
            children: [
              FirstTab(),
              SecondTab(),
              ThirdTab(),
              FourthTab(),
              FifthTab(),
            ],
          ),
          SafeArea(
            child: Padding(
              padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 16),
              child: Column(
                children: [
                  Container(
                    alignment: Alignment.topCenter,
                    child: TabPageSelector(
                      color: DefaultTabController.of(context)?.index == 1
                          ? Colors.black38
                          : Colors.grey[400],
                      selectedColor:
                          DefaultTabController.of(context)?.index == 1
                              ? Colors.white
                              : Colors.black26,
                      indicatorSize: 8,
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
