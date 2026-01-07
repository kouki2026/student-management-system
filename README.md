# Week 5 OOP応用版学生管理システム

## 概要
継承・ポリモーフィズム・抽象化・例外処理・Lombokを統合したエンタープライズレベルの学生管理システムです。

## 技術仕様
- **継承階層**: Person → Student → 各学生タイプ
- **ポリモーフィズム**: 統一インターフェースによる多様な処理
- **抽象化**: インターフェース・抽象クラスによる設計品質確保
- **例外処理**: カスタム例外による堅牢なエラーハンドリング
- **現代的技術**: Lombokによる開発効率化

## ファイル構成
- Person.java: 基底クラス
- Student.java: 学生抽象クラス
- UndergraduateStudent.java: 学部生クラス
- GraduateStudent.java: 大学院生クラス
- InternationalStudent.java: 留学生クラス
- StudentManagementSystem.java: システムメインクラス

## 実行方法
1. Eclipse で本プロジェクトを開く
2. StudentManagementSystem.java を実行
3. メニューに従って操作

## 作成者
Week 5学習者：エンタープライズJavaアーキテクト