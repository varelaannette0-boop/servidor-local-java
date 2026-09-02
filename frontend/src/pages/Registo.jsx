import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Registo() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");

  const navigate = useNavigate();

  const fazerRegisto = async (e) => {
    e.preventDefault();

    try {
      const resposta = await fetch(
        `${import.meta.env.VITE_API_URL}/auth/registar`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            username,
            password,
            email,
          }),
        }
      );

      const dados = await resposta.json();

      if (resposta.ok) {
        alert("Conta criada com sucesso!");
        navigate("/");
      } else {
        alert(dados.message || dados.erro || "Erro ao criar conta.");
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
          <h1>Criar Conta ✨</h1>
          <p>Preencha os dados para criar a sua conta</p>
        </div>

        <form onSubmit={fazerRegisto} className="auth-form">

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
            <label>Email</label>
            <input
              type="email"
              placeholder="Digite o seu email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
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
            Registar
          </button>

        </form>

        <div className="auth-footer">
          <p>Já tem uma conta?</p>

          <button
            type="button"
            className="secondary-button"
            onClick={() => navigate("/")}
          >
            Voltar para Login
          </button>
        </div>

      </div>
    </div>
  );
}

export default Registo;