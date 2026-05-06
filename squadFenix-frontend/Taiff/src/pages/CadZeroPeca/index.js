import React, { Component } from "react";
import api from "../../api";
import Header from "../../Header";
import Footer from "../../Footer";
import Swal from "sweetalert2";
import './ZeroPeca.css';
import '../CSSbotao.css';

class CadZeroPeca extends Component{

    constructor(props){
        super(props);
        this.state={
            x:"",
            y:"",
            z:"",
            r:"",
            
        }
        this.handleChangeX = this.handleChangeX.bind(this);
        this.handleChangeY = this.handleChangeY.bind(this);
        this.handleChangeZ = this.handleChangeZ.bind(this);
        this.handleChangeR = this.handleChangeR.bind(this);

    }
    
    handleChangeX(event) {
        this.setState({x: event.target.value});
      };
    
      handleChangeY(event) {
        this.setState({y: event.target.value});
      };
    
      handleChangeZ(event) {
        this.setState({z: event.target.value});
      };
    
      handleChangeR(event) {
        this.setState({r: event.target.value});
      };


      onRequest = async (e) =>{
        e.preventDefault();

        const{x,y,z,r} = this.state;
    
        try{
            const res = await api.post('/zeropeca', {
                x: Number(x),
                y: Number(y),
                z: Number(z),
                r: Number(r)
            });
            if(!x || !y || !z || !r){
                Swal.fire({
                    title:'Error',
                    text:'Preencha todos os Campos!',
                    cancelButtonText:'OK'
                })
                return;
            }
            window.location.href = "/cadTeste";
        } catch (error) {
            console.log('não deu certo', error);
        }
    
      };

render(){
    return(
        <div className="container">
            <Header/>
                <div className="card-post">
                    <h1>Cadastro Zero Peça</h1>
                    <form onSubmit={this.onRequest}>
                        <div className="fields">
                            
                            <input type="text" id="x" value={this.state.x} onChange={this.handleChangeX} className="campo" name="Eixo-X" placeholder="Eixo-X" />
                        </div>

                        <div className="fields">
                            
                            <input type="text" id="y" value={this.state.y} onChange={this.handleChangeY} className="campo" name="Eixo-Y" placeholder="Eixo-Y" />
                        </div>
                        <div className="fields">
                            
                            <input type="text" id="z" value={this.state.z} onChange={this.handleChangeZ} className="campo" name="Eixo-Z" placeholder="Eixo-Z"/>
                        </div>

                        <div className="fields">
                            
                            <input type="text" id="r" value={this.state.r} onChange={this.handleChangeR} className="campo" name="Rotação" placeholder="Rotação"/>
                        </div>

                        <div className="btn-zeroPeca" >
                                <button className="botao" type="submit">
                                    Enviar
                                </button> 
                            </div>
                    </form>
                </div>
            <Footer/>
        </div>
    );
}

}

export default CadZeroPeca;