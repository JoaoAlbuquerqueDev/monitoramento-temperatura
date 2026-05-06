import React, { Component, useState, useEffect, useRef} from "react";
import { Line } from 'react-chartjs-2';
import { useNavigate, useLocation } from "react-router-dom";
import {
  Chart as ChartJS,
  LineElement,
  PointElement,
  CategoryScale,
  LinearScale,
  TimeScale,
  Tooltip,
  Legend
} from 'chart.js';
import 'chartjs-adapter-luxon';
import {Link} from 'react-router-dom';
import api from "../../api";
import Header from "../../Header";
import Footer from "../../Footer";
import "./Grafico.css"
import '../CSSbotao.css';

ChartJS.register(
  LineElement,
  PointElement,
  CategoryScale,
  LinearScale,
  TimeScale,
  Tooltip,
  Legend,
);

export default function GraficoTempoReal(){
    const[rodando, setRodando] = useState(true);
    const navigate = useNavigate();
    
    const chartRef = useRef();
    const[dados, setDados] = useState({
        labels: [],
        t1: [],
        t2: [],
        t3: [],
        tamb: []
    });

    useEffect(() => {
    const interval = setInterval(() => {
      buscarUltimaTemperatura();
    }, 1000); // atualiza a cada 1s

    return () => clearInterval(interval);
  }, []);

async function buscarUltimaTemperatura() {
    if(!rodando) return
  try {
    const res = await api.get("/temperatura/ultima");
    const t = res.data;

    if (!t) return;

    setDados((prev) => {
      if (Math.abs(prev.t1.at(-1) - t.t1) < 0.01) return prev;

      const max = 50;

      return {
        labels: [...prev.labels, new Date()].slice(-max),
        t1: [...prev.t1, t.t1].slice(-max),
        t2: [...prev.t2, t.t2].slice(-max),
        t3: [...prev.t3, t.t3].slice(-max),
        tamb: [...prev.tamb, t.tambiente].slice(-max),
      };
    });

  } catch (err) {
    console.error("Erro ao buscar temperatura:", err);
  }
}

async function iniciarSimulacao() {
  try {
    api.post("/temperatura/stream-simulacao");
  } catch (err) {
    console.error(err);
  }
};

async function gerarRelatorio() {
    try {
        await api.post("/temperatura/parar"); // para o stream no back

        const chart = chartRef.current;

        setRodando(false);//para o grafico no front

        if (!chart) return;

        const imagem = chart.toBase64Image("image/png", 1);

        navigate("/relatorio",{
            state: {grafico: imagem}
        })// redireciona para o relatorio e passa a imagem do grafico

    } catch (err) {
        console.error(err);
    }
}       
        const LIMITE = 120;
        const data = {
            labels: dados.labels,
            datasets: [
                        {
                        label: 'Termopar A',
                        data: dados.tamb,
                        fill: false,
                        tension: 0.4,
                        backgroundColor: (ctx) => {
                            const value = ctx.raw;
                            return value > 120 
                            ? 'rgba(255,0,0,0.3)' 
                            : 'rgba(148,0,211,0.50)';
                        },
                        
                       segment: {
                            borderColor: ctx => {
                            return ctx.p1.parsed.y > 120 ? 'red' : 'rgba(148,0,211,0.50)';
                            }
                        },              
                            
                        borderWidth: 2
                        },
                        {
                        label: 'Termopar 1',
                        data: dados.t1,
                        fill: false,
                        tension: 0.4,
                        backgroundColor: (ctx) => {
                            const value = ctx.raw;
                            return value > 120 
                            ? 'rgba(255,0,0,0.3)' 
                            : 'rgba(0,0,128,0.50)';
                        },

                        segment: {
                            borderColor: ctx => {
                            return ctx.p1.parsed.y > 120 ? 'red' : 'rgba(0,0,128,0.50)';
                            }
                        },   
                        borderWidth: 2
                        },
                        {
                            label: 'Termopar 2',
                            data: dados.t2,
                            fill: false,
                            tension: 0.4,
                            backgroundColor: (ctx) => {
                                const value = ctx.raw;
                                return value > 120 
                                ? 'rgba(255,0,0,0.3)' 
                                : 'rgba(139,0,0,0.50)';
                            },

                            segment: {
                                borderColor: ctx => {
                                return ctx.p1.parsed.y > 120 ? 'red' : 'rgba(139,0,0,0.50)';
                                }
                            },   
               
                            borderWidth: 2
                        },
                        {
                            label: 'Termopar 3',
                            data: dados.t3,
                            fill: false,
                            tension: 0.4,
                            backgroundColor: (ctx) => {
                                const value = ctx.raw;
                                return value > 120 
                                ? 'rgba(255,0,0,0.3)' 
                                : 'rgba(0,100,0,0.50)';
                            },

                            segment: {
                                borderColor: ctx => {
                                return ctx.p1.parsed.y > 120 ? 'red' : 'rgba(0,100,0,0.50)';
                                }
                            },               
                             
                            borderWidth: 2
                        },
                        {
                            label: 'Limite',
                            data: dados.labels.map(() => 120),
                            borderColor: 'red',
                            borderWidth: 2,
                            borderDash: [6, 6],
                            pointRadius: 0,
                            fill: false,
                            tension: 0
                        }
                    ],
        }
        const options = {
            maintainAspectRatio: false,
            responsive: true,
            plugins:{
                legend:{
                    labels:{
                        color: '#383636'
                    }
                }
            },
            scales: {
                x:{
                    type: 'time',
                    time:{
                        unit: 'second',
                        tooltipFormat: 'HH:mm:ss'
                    },
                    ticks: {color: '#383636'}
                },
                y:{
                    ticks:{color: '#383636'}
                }    
            }
        }
        
    return(
        <div className="container">
            <Header/>

            <div className="grafico">
                <Line
                    ref={chartRef}
                    data={data}
                    options={options}
                />
            </div>

            <div className="botoes">
                <a className="botao" href="http://localhost:8080/temperaturacsv">
                    Baixar CSV
                </a>

                <button className="botao" onClick={gerarRelatorio}>Gerar Relatório</button> 

                <button className="botao" onClick={iniciarSimulacao}>
                    Iniciar Simulação
                </button>
            </div>
 
            <Footer/>
        </div>    
    )
};

