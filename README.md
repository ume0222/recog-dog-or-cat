# 使い方
1. Gradleプロジェクトとしてインポート
2. [https://aimaker.io/]()にアクセスして、twitter連携をする
3. [https://aimaker.io/app/image-classification/id/5962]()に行き、ページの下のほうの「APIを使う！」でAPIキーを確認する
4. src/main/resources内にaimaker.propertiesを作成し、以下の2行を追加  
aimaker.id=5962  
aimaker.apikey=確認したAPIキー
5. MySQLをインストールし、springデータベースとユーザ(ユーザ名：user, パスワード：password, springデータベースへのアクセス権限を持つ)を作成する
6. サーバで実行する
