# 使い方
1. Gradleプロジェクトとしてインポート
2. https://aimaker.io/にアクセスして、twitter連携をする
3. https://aimaker.io/app/image-classification/id/5962に行き、ページの下のほうの「APIを使う！」でAPIキーを確認する
4. 確認したAPIキーをaimaker.propertiesのaimaker.apikey=に続いて書く
5. (今は使ってないけどデータベース設定が必要なので)MySQLをインストールし、  
springデータベースとユーザ(ユーザ名：user, パスワード：password, springデータベースへのアクセス権限を持つ)を作成する
6. サーバで実行する
