# 🤖 Joker App – Final Documentation

## 📡 API Used

The app uses a free API called **[JokeAPI](https://jokeapi.dev/)** (sourced from APIVault) that returns jokes in JSON format. This allows the app to fetch live jokes from the internet and display them based on the user’s preferences.

---

## 🛠 Technical Steps

1. Used **Retrofit** to handle HTTP requests to the JokeAPI.
2. Created an **interface** using `@GET` to define the API endpoints.
3. Defined a **data model** to map the JSON response.
4. Fetched jokes from the API and **displayed them in a `TextView`**, applying filters like category, type, and language.

The app enables users to fetch live jokes with control over the type and category of jokes shown.

---

## 📱 How to Use the Joke App

### 1. Open the App
- Launch the app and tap **“Get Started”** on the welcome screen.

### 2. Select Your Preferences
- Choose **joke categories** (e.g., Programming, Dark, Pun).
- Pick the **joke type**:
  - `Single`: One-liner jokes.
  - `Two-part`: Setup and punchline format.
- Select a **language** (e.g., English).

### 3. Get a Joke
- Tap **“Get Joke”** to fetch and view a random joke.

### 4. Want Another?
- Tap **“Get Joke”** again to see a new one.

---

Masixole Ncoko
ST10205783
