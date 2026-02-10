# Maestro UI Testing Sample

[Maestro](https://maestro.mobile.dev/) を使った Android E2E テストのサンプルプロジェクトです。

## アプリ概要

名前を入力して挨拶を表示するシンプルな Jetpack Compose アプリです。

- 名前を入力して「挨拶する」ボタンをタップすると `Hello, {名前}` が表示される
- 名前が16文字以上の場合、バリデーションエラーが表示される

## 技術スタック

- Kotlin
- Jetpack Compose (Material Design 3)
- Maestro (E2E テスト)

## Maestro テスト

`.maestro/` ディレクトリにテストフローが格納されています。

| ファイル | 内容 |
|---|---|
| `greeting_test.yaml` | 名前を入力して挨拶が表示されることを確認 |
| `validation_ok_test.yaml` | 15文字ちょうどならエラーなしで挨拶できることを確認 |
| `validation_error_test.yaml` | 16文字以上でバリデーションエラーが表示されることを確認 |

## セットアップ

### 前提条件

- Android Studio
- Android エミュレータ or 実機
- Maestro CLI

### Maestro のインストール

```bash
curl -Ls "https://get.maestro.mobile.dev" | bash
```

### テストの実行

1. エミュレータを起動し、アプリをインストールする
2. 全テストを実行:

```bash
maestro test .maestro/
```

個別テストの実行:

```bash
maestro test .maestro/greeting_test.yaml
```

## ポイント

Maestro でCompose の要素を識別するために、`testTag` と `testTagsAsResourceId` を組み合わせています。

```kotlin
// Modifier に testTag を付与
Modifier.testTag("nameTextField")

// semantics ブロックで testTag をリソース ID として扱う
Modifier.semantics { testTagsAsResourceId = true }
```

これにより、Maestro の YAML から `id` で要素を指定できます:

```yaml
- tapOn:
    id: "nameTextField"
```