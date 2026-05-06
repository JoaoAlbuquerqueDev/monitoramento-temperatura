import React, {Component} from "react";
import api from "../../api";
import Header from '../../Header'
import Footer from '../../Footer'
import './Form.css';
import '../CSSbotao.css';
import Swal from "sweetalert2";



class cadPosicoes extends Component{
    constructor(props){
    super(props);
    var ultimo;
    this.state={
      lista: [],
        eixoX:"",
        eixoY:"",
        eixoZ:"",
        rotacao:"",
        tempo:"",
        teste:"",
        ultimo: null
    }
    this.handleChangeX = this.handleChangeX.bind(this);
    this.handleChangeY = this.handleChangeY.bind(this);
    this.handleChangeZ = this.handleChangeZ.bind(this);
    this.handleChangeR = this.handleChangeR.bind(this);
    this.handleChangeT = this.handleChangeT.bind(this);

}

handleChangeX(event) {
    this.setState({eixoX: event.target.value});
  };

  handleChangeY(event) {
    this.setState({eixoY: event.target.value});
  };

  handleChangeZ(event) {
    this.setState({eixoZ: event.target.value});
  };

  handleChangeR(event) {
    this.setState({rotacao: event.target.value});
  };

  handleChangeT(event) {
    this.setState({tempo: event.target.value});
  };

  onRequest = async (event) =>{
  event.preventDefault(); // evita reload

  const {eixoX, eixoY, eixoZ, rotacao, tempo, ultimo} = this.state

  if (!ultimo) {
    alert("ID inválido");
    return;
  }
  console.log("ID enviado:", ultimo);

  try{
    const res = await api.post('/gravarposicao', {
      eixoX: Number(eixoX),
      eixoY: Number(eixoY),
      eixoZ: Number(eixoZ),
      rotacao: Number(rotacao),
      tempo: Number(tempo),
      ultimo,
      teste: ultimo
    });
    if (!eixoX || !eixoY || !eixoZ || !rotacao || !tempo) {
      Swal.fire({
        title: 'Error',
        text: 'Preencha todos os Campos',
        confirmButtonText: 'OK'
      })
      return;
    }
    Swal.fire({
      text: 'Posição cadastrada com Sucesso',
      icon:'success',
      confirmButtonText:'OK'
    })
    // limpa os campos
    this.setState({
      eixoX: "",
      eixoY: "",
      eixoZ: "",
      rotacao: "",
      tempo: ""
    });
    return res.data
  } catch (error) {
    console.log('não deu certo', error);
  }
};

  async componentDidMount(){
    const response = await api.get('/testes');
    const lista = response.data;
    const listaID = lista.map(item=> item.id);
    const ultimo = listaID.length > 0 ? listaID[listaID.length - 1] : null;
    this.setState({
      lista:lista,
      ultimo: ultimo
    });
  }

render(){
  const {lista} = this.state;
    console.log(lista);

  return(
    <div className="container">
      <Header/>
        <main>
            <div className="card-post">

                <h1>Cadastro de Posições</h1>
                <div className="line-post"></div>

                <div className="card-body-post">

                    <form onSubmit={this.onRequest}>
                        <div className="fields">
                            
                            <input type="text" id="x" value={this.state.eixoX} onChange={this.handleChangeX} className="campo" name="Eixo-X" placeholder="Eixo-X" />
                        </div>

                        <div className="fields">
                            
                            <input type="text" id="y" value={this.state.eixoY} onChange={this.handleChangeY} className="campo" name="Eixo-Y" placeholder="Eixo-Y" />
                        </div>

                        <div className="fields">
                          
                            <input type="text" id="z" value={this.state.eixoZ} onChange={this.handleChangeZ} className="campo" name="Eixo-Z" placeholder="Eixo-Z" />
                        </div>

                        <div className="fields">
                            
                            <input type="text" id="rot" value={this.state.rotacao} onChange={this.handleChangeR} className="campo" name="Rotação" placeholder="Rotação" />
                        </div>

                        <div className="fields">
                            
                            <input type="text" id="temp" value={this.state.tempo} onChange={this.handleChangeT} className="campo" name="Tempo(s)" placeholder="Tempo(s)" />
                        </div>

                        <div>
                            <button className="botao" type="submit">Salvar</button>
                        </div>
                    </form>

                </div>

            </div>
        </main>
      <Footer/>
    </div>
  );


}
}
export default cadPosicoes;