import { useEffect, useState } from "react";

function Dashboard() {
  const [servicos, setServicos] = useState([]);
  const [carregando, setCarregando] = useState(true);

  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [preco, setPreco] = useState("");
  const [estaAtivo, setEstaAtivo] = useState(true);

  const buscarServicos = async () => {
    try {
      const token = localStorage.getItem("meu_token");

      const resposta = await fetch(
        `${import.meta.env.VITE_API_URL}/v1/servicos?page=0&size=10&sort=titulo,ASC`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      const dados = await resposta.json();

      console.log("SERVIÇOS:", dados);

      if (!resposta.ok) {
        throw new Error("Erro ao buscar serviços.");
      }

      setServicos(dados.content || []);
    } catch (erro) {
      console.error(erro);
      alert("Erro ao carregar os serviços.");
    } finally {
      setCarregando(false);
    }
  };

  useEffect(() => {
    buscarServicos();
  }, []);

  const criarServico = async (e) => {
    e.preventDefault();

    try {
      const token = localStorage.getItem("meu_token");

      const resposta = await fetch(
        `${import.meta.env.VITE_API_URL}/v1/servicos`,
        {
          method: "POST",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
  titulo,
  descricao,
  preco: Number(preco),
  estaAtivo,
  precoComDesconto: Number(preco),
  imagemCapa: null,
}),
        }
      );

      const dados = await resposta.json();

      console.log("SERVIÇO CRIADO:", dados);

      if (!resposta.ok) {
        alert(dados.mensagem || dados.message || "Erro ao criar serviço.");
        return;
      }

      alert("Serviço criado com sucesso!");

      setTitulo("");
      setDescricao("");
      setPreco("");
      setEstaAtivo(true);

      buscarServicos();
    } catch (erro) {
      console.error(erro);
      alert("Erro ao ligar ao servidor.");
    }
  };

  const fazerLogout = () => {
    localStorage.removeItem("meu_token");
    window.location.href = "/";
  };

  return (
    <div>
      <h1>Dashboard</h1>

      <button onClick={fazerLogout}>
        Logout
      </button>

      <hr />

      <h2>Criar Serviço</h2>

      <form onSubmit={criarServico}>
        <div>
          <label>Título</label>
          <br />

          <input
            type="text"
            value={titulo}
            onChange={(e) => setTitulo(e.target.value)}
            required
          />
        </div>

        <br />

        <div>
          <label>Descrição</label>
          <br />

          <textarea
            value={descricao}
            onChange={(e) => setDescricao(e.target.value)}
            required
          />
        </div>

        <br />

        <div>
          <label>Preço</label>
          <br />

          <input
            type="number"
            step="0.01"
            value={preco}
            onChange={(e) => setPreco(e.target.value)}
            required
          />
        </div>

        <br />

        <div>
          <label>
            <input
              type="checkbox"
              checked={estaAtivo}
              onChange={(e) => setEstaAtivo(e.target.checked)}
            />
            {" "}Serviço ativo
          </label>
        </div>

        <br />

        <button type="submit">
          Criar Serviço
        </button>
      </form>

      <hr />

      <h2>Serviços</h2>

      {carregando ? (
        <p>A carregar serviços...</p>
      ) : servicos.length === 0 ? (
        <p>Nenhum serviço encontrado.</p>
      ) : (
        <div>
          {servicos.map((servico) => (
            <div key={servico.id}>
              <h3>{servico.titulo}</h3>

              <p>{servico.descricao}</p>

              <p>Preço: {servico.preco}</p>

              <p>
                Estado: {servico.estaAtivo ? "Ativo" : "Inativo"}
              </p>

              <hr />
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Dashboard;