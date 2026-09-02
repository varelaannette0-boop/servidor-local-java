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
    <div className="auth-page">
      <div className="auth-card">
        <div className="auth-header">
          <h1>Bem-vindo 👋</h1>
          <p>Entre na sua conta para continuar</p>
        </div>

        <form onSubmit={fazerLogin} className="auth-form">
          <div className="form-group">
            <label>Username</label>
            <input
              type="text"
              placeholder="Digite o seu username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              placeholder="Digite a sua password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="primary-button">
            Entrar
          </button>
        </form>

        <div className="auth-footer">
          <p>Ainda não tem uma conta?</p>

          <button
            type="button"
            className="secondary-button"
            onClick={() => navigate("/registo")}
          >
            Criar conta
          </button>
        </div>
      </div>
    </div>
  );
}

export default Login;