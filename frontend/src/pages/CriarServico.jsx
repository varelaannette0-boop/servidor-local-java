import { useState } from "react";

function CriarServico() {
  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [preco, setPreco] = useState("");
  const [estaAtivo, setEstaAtivo] = useState(true);
  const [precoComDesconto, setPrecoComDesconto] = useState(0);

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

      console.log("RESPOSTA:", dados);

      if (resposta.ok) {
        alert("Serviço criado com sucesso!");

        setTitulo("");
        setDescricao("");
        setPreco("");
        setEstaAtivo(true);
      } else {
        alert(dados.message || "Erro ao criar serviço.");
      }
    } catch (erro) {
      console.error(erro);
      alert("Erro ao ligar ao servidor.");
    }
  };

  return (
    <div className="" style={{backgroundColor: "red", width: "100%"}}>
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
    </div>
  );
}

export default CriarServico;