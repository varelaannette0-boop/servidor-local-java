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
        alert(
          dados.mensagem ||
            dados.message ||
            "Erro ao criar serviço."
        );
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
    <div className="dashboard-page">

      {/* Cabeçalho */}
      <header className="dashboard-header">
        <div>
          <h1>Dashboard</h1>
          <p>Gerencie os seus serviços</p>
        </div>

        <button
          onClick={fazerLogout}
          className="logout-button"
        >
          Sair
        </button>
      </header>

      <main className="dashboard-content">

        {/* Criar serviço */}
        <section className="service-form-card">
          <div className="section-title">
            <h2>➕ Criar Serviço</h2>
            <p>Adicione um novo serviço à plataforma</p>
          </div>

          <form onSubmit={criarServico} className="service-form">

            <div className="form-group">
              <label>Título</label>

              <input
                type="text"
                placeholder="Digite o título do serviço"
                value={titulo}
                onChange={(e) => setTitulo(e.target.value)}
                required
              />
            </div>

            <div className="form-group">
              <label>Descrição</label>

              <textarea
                placeholder="Digite a descrição do serviço"
                value={descricao}
                onChange={(e) => setDescricao(e.target.value)}
                required
              />
            </div>

            <div className="form-group">
              <label>Preço</label>

              <input
                type="number"
                step="0.01"
                min="0"
                placeholder="0.00"
                value={preco}
                onChange={(e) => setPreco(e.target.value)}
                required
              />
            </div>

            <div className="checkbox-group">
              <label>
                <input
                  type="checkbox"
                  checked={estaAtivo}
                  onChange={(e) =>
                    setEstaAtivo(e.target.checked)
                  }
                />

                <span>Serviço ativo</span>
              </label>
            </div>

            <button
              type="submit"
              className="create-button"
            >
              Criar Serviço
            </button>

          </form>
        </section>

        {/* Lista de serviços */}
        <section className="services-section">

          <div className="section-title">
            <h2>📋 Serviços</h2>
            <p>Serviços disponíveis na plataforma</p>
          </div>

          {carregando ? (
            <div className="loading">
              <p>A carregar serviços...</p>
            </div>
          ) : servicos.length === 0 ? (
            <div className="empty-state">
              <p>Nenhum serviço encontrado.</p>
            </div>
          ) : (
            <div className="services-grid">

              {servicos.map((servico) => (
                <div
                  className="service-card"
                  key={servico.id}
                >
                  <div className="service-card-header">

                    <h3>{servico.titulo}</h3>

                    <span
                      className={
                        servico.estaAtivo
                          ? "status active"
                          : "status inactive"
                      }
                    >
                      {servico.estaAtivo
                        ? "Ativo"
                        : "Inativo"}
                    </span>

                  </div>

                  <p className="service-description">
                    {servico.descricao}
                  </p>

                  <div className="service-price">
                    {servico.preco} €
                  </div>
                </div>
              ))}

            </div>
          )}

        </section>

      </main>
    </div>
  );
}

export default Dashboard;