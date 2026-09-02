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
        alert(dados.message || "Erro ao criar conta.");
      }
    } catch (erro) {
      console.error(erro);
      alert("Erro ao ligar ao servidor.");
    }
  };

  return (
    <div>
      <h1>Criar Conta</h1>

      <form onSubmit={fazerRegisto}>
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
          <label>Email</label>
          <br />

          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
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
          Registar
        </button>
      </form>

      <br />

      <button onClick={() => navigate("/")}>
        Voltar para Login
      </button>
    </div>
  );
}

export default Registo;