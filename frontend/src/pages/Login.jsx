import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const fazerLogin = async (e) => {
    e.preventDefault();

    try {
      const resposta = await fetch(
        `${import.meta.env.VITE_API_URL}/auth/login`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            username,
            password,
          }),
        }
      );

      const token = await resposta.text();

      if (resposta.ok) {
        localStorage.setItem("meu_token", token);

        alert("Login efetuado com sucesso!");

        navigate("/dashboard");
      } else {
        alert("Username ou password incorretos.");
      }
    } catch (erro) {
      console.error(erro);
      alert("Erro ao ligar ao servidor.");
    }
  };

  return (
    <div>
      <h1>Login</h1>

      <form onSubmit={fazerLogin}>
        <div>
          <label>Username</label>
          <br />

          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>

        <br />

        <div>
          <label>Password</label>
          <br />

          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>

        <br />

        <button type="submit">
          Entrar
        </button>
      </form>

      <br />

      <button onClick={() => navigate("/registo")}>
        Criar conta
      </button>
    </div>
  );
}

export default Login;