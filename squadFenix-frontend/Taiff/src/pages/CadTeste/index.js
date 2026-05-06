import React, { Component } from "react";
import api from "../../api";
import Header from "../../Header";
import Footer from "../../Footer";
import Swal from "sweetalert2";


import '../CSSbotao.css';

class CadTeste extends Component{

    constructor(props){
        super(props);
        this.state={
            lista:[],
            nome:"",
            modelo:"",
            dataTeste:"",
            idZeroPeca:""
        }
        this.handleChangeN = this.handleChangeN.bind(this);
        this.handleChangeM = this.handleChangeM.bind(this);
        this.handleChangeD = this.handleChangeD.bind(this);
    
    }
    
    handleChangeN(event) {
        this.setState({nome: event.target.value});
      };
    
      handleChangeM(event) {
        this.setState({modelo: event.target.value});
      };

      handleChangeD(event) {
        this.setState({dataTeste: event.target.value});
      };

        
      onRequest = async (e) =>{
        e.preventDefault();

        const{nome, modelo, dataTeste, idZeroPeca} = this.state;

        if (!idZeroPeca) {
            alert("Zero peça não encontrada!");
            return;
        }
    
        try{
            const res = await api.post('/gravarteste', {
                nome,
                modelo,
                dataTeste,
                idZeroPeca
            });
            if(!nome || !modelo || !dataTeste){
                Swal.fire({
                    title:'Error',
                    text:'Preencha todos os Campos',
                    confirmButtonText:'OK'
                })
                return;
            }
            window.location.href = "/cadPosicoes";
            return res.data
        } catch (error) {
            console.log('não deu certo', error);
        }
    
      };

      async componentDidMount(){
        const response = await api.get('/zeroPeca');
        const lista = response.data
        const listaID = lista.map(item => item.id)
        const ultimo = listaID.length > 0 ? listaID[listaID.length -1] : null;

        this.setState({
            lista,
            idZeroPeca: ultimo
        });

      }

render(){
    const {lista} = this.state;
    console.log(lista);

    return(

        <div className="container">
            <Header/>

            <div className="card-post">
                <h1> Cadastro de Teste</h1>
                    <form onSubmit={this.onRequest}>
                        <div className="fields">
                            
                            <input type="text" id="n" value={this.state.nome} onChange={this.handleChangeN} className="campo" name="Nome" placeholder="Nome"/>
                        </div>

                        <div className="fields">
                            
                            <input type="text" id="m" value={this.state.modelo} onChange={this.handleChangeM} className="campo" name="Modelo" placeholder="Modelo"/>
                        </div>

                        <div className="fields">
                            
                            <input type="date" id="d" value={this.state.dataTeste} onChange={this.handleChangeD} className="campo" name="Data" placeholder="xx/xx/xxxx"/>
                        </div>

                        <div className="btn-produto">
                           <button className="botao" type="submit">
                                Salvar
                            </button>
                        </div>
                    </form>
                </div>
            <Footer/>
        </div>
    );
}

}

export default CadTeste;