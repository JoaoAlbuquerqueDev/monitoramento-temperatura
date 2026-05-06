import pdfMake from 'pdfmake/build/pdfmake'
import pdfFonts from 'pdfmake/build/vfs_fonts'
import process from 'process';


function relatorioPDF(medias, nome, data, local, tipoProduto, especificar, fabricante, modeloPlaca, freqPlaca, pais, nomeComercial, tensPlaca, potPlaca, grafico){

    window.process = process
    pdfMake.vfs = pdfFonts.vfs;

    const LIMITE = 120;

    const lista = Array.isArray(medias) ? medias : [medias];

    const listaSegura = lista.filter(item => item && typeof item === "object");
    
    const format = (valor) => {
        if (valor === null || valor === undefined) return "-";
        if (typeof valor === "number") return valor.toFixed(1);
        return valor;
    };

    const dados = listaSegura.map(media => [
        media?.id ?? "-",
        format(media?.mediaTA),
        format(media?.mediaT1),
        format(media?.mediaT2),
        format(media?.mediaT3),
        format(media?.mediaM),
        format(media?.mediaCor)
    ]);

    // Verificação automática
    const temAlerta = listaSegura.some(m =>
        (m.mediaTA ?? 0) > LIMITE ||
        (m.mediaT1 ?? 0) > LIMITE ||
        (m.mediaT2 ?? 0) > LIMITE ||
        (m.mediaT3 ?? 0) > LIMITE
    );

    const docDef = {
        pageSize: 'A4',
        pageMargins: [15,120,15,40],

        header: [
            {
                columns: [
                    { image: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAT4AAACfCAYAAABgD7XPAAAqNklEQVR4Xu2dCXgURfr/JwlJICGAQU7/qwFEQMKdcN83CHIoSFgROV1ALjkVFC/2p6irK4ir64XgAaiAiCC4yH0k3AKCiAIuNwQQQi6S/L/fzhQ2w0ympnsSE/ft55knx3R1VX26+u33feuttxwOOYSAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAv4lEODfy9m72v59+0JwhcL4ZDmvFIifhZyfIPzk3/yw3e7arsp5aoi5jGv5nFh4u65RX1p6emhyUlJRd21LTU8PLl2q1Nm7q1c/ZI+S59KnTp4qGhIaknLx4sXIlOTk8KysrMDfLl0q4aGEa5888kDbQ51t/1Gn7WhH5IGDB6qGBgen5nC+uX6P7MmtapWq+8qWK3tZp26756DtYU6GJckQ9zSEPNGXtCLh4VdC8BNcjfYGBARojQtnm7w9ax7HJu9j+rVrhW6//fYjkZGRyXb7yPKJiYl8jhzom9FP9glj5Rb+T/WTv1vsq24Tb2DCutDP4LJlyh7H/b6iexEr51Go/CHH8OHDA3756fCcwiHB5dGATHySpkyalOZsjBJuxvjCxyzslNDzJPxUf9SgVHDV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G25TeFtpi6qHfkW4HdZh8/aL3uqGs7/J+tXf3YsXkvHy87VexZq8EQeahN3b/vBMv+76TL6YxOus3DpW+mnmW7te3W1wI/htTbi3+/Rn+D5fzOpiP4WOCDdoVSakqCM11dISQ6R8zQq45MjOwIPNwpe7xtTR9zZ61KgZmPV6hH6roIxCjmshOSwHSMty8Hq3RBS/Bg1xyKAhgz9wd8MRcF2pY/sOc7HZUCNet9DljGDjumlZ2S+VnOrI7RGEPrD+82lXuHJhbU4+T5OQDIIboBPuRws8mCmJp88VLhIUbKmll1Bv3burf89d7XTWyH74/gcDoAWFchxcs1Sjw3EtNDANL78QOPv3t2nTxuvklsVqbBXDErV79nz/fQ3TeKegd5cINcd6yPfW0qVPx8XFzYHP2Vab/tcK21gI5D9U3Ih7//4fqoUXKUL/k6c2ufu/q/YVULho+BX4dlZD47thWpDOem5Y41KH1/6HFy/2GzZ7XunpwUXb4ZP6obqb63pKRu8/cD5cqUbtWrurR0d7XcoE7TAAmZYbHT70053ok8rlRk6KlXa/kpKTw0qXLXOK+/XifnhNwopMOJ2xjeitLvX60MvsU531nu7atWu+FHzcuuDMqdOlnGOGPM18vfX3JosDoVifeSsk3wsBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBISAEBACQkAICAEhIASEgBAQAkJACAgBISAEhIAQEAJCQAgIASEgBIRA7hL4/92SSxuUPl/oAAAAAElFTkSuQmCC', width: 120 },
                    { text: "LAUDO TÉCNICO", alignment: "right", margin:[0,20,10,0], fontSize: 16, bold: true }
                ]
            },

            {
                table: {
                    widths: ['*','*','*'],
                    body: [
                        ['EXECUTANTE:', 'DATA:', 'LOCAL:'],
                        [{text:nome}, {text:data}, {text:local}]
                    ]
                }
            }
        ],

        content: [

        { text: 'IDENTIFICAÇÃO DO PRODUTO', bold: true, margin: [0,20,0,10] },

        {
            table: {
                widths: ['*','*'],
                body: [
                    ['Produto', 'Especificação'],
                    [tipoProduto, especificar]
                ]
            }
        },

        {
            table: {
                widths: ['*','*','*','*'],
                body: [
                    ['Fabricante','Modelo','Frequência','País'],
                    [fabricante, modeloPlaca, freqPlaca, pais]
                ]
            },
            margin:[0,10,0,0]
        },

        {
            table: {
                widths: ['*','*','*'],
                body: [
                    ['Nome Comercial','Tensão','Potência'],
                    [nomeComercial, tensPlaca, potPlaca]
                ]
            },
            margin:[0,10,0,0]
        },

        { text: 'DADOS COLETADOS', bold: true, margin:[0,20,0,10] },

        {
            table: {
                headerRows: 1,
                widths: [30, 40, 40, 40, 40, 40, 40],
                body: [
                    ['ID','TA','T1','T2','T3','TM','TCorr'],
                    ...(dados.length ? dados : [["-","-","-","-","-","-","-"]])
                ]
            }
        },
        {
            text: 'GRÁFICO DE TEMPERATURA',
            bold: true,
            margin: [0,20,0,10],
            pageBreak: 'before'
        },
         grafico ?{
            image: grafico,
            width: 500,
            margin: [0,10,0,10],
            alignment: 'center'
        }:{
            text:'Gráfico não disponível',
            color: 'red'
        },
        { text: 'RESULTADO FINAL', bold: true, margin:[0,20,0,10] },

        {
            text: temAlerta ? 'REPROVADO' : 'APROVADO',
            color: temAlerta ? 'red' : 'green',
            fontSize: 14,
            bold: true
        },

        {
            text: temAlerta 
                ? 'Temperatura acima do limite permitido.'
                : 'Equipamento dentro do padrão.',
            margin:[0,10,0,10]
        },

        { text: 'ASSINATURA', bold:true, margin:[0,40,0,10] },
        {
            canvas: [
                {
                    type: 'line',
                    x1: 0, y1: 0,
                    x2: 200, y2: 0,
                    lineWidth: 1
                }
            ]
        }

        ],

        footer: {
            text: 'Documento técnico - Controle interno',
            alignment: 'center',
            fontSize: 8
        }
    };

    pdfMake.createPdf(docDef).open();
    
    // para não abrir automaticamente - pdfMake.createPdf(docDef).download("relatorio.pdf");
    //para colocar um nome dinâmico no arquivo - .download(`relatorio_${nome}.pdf`);
}

export default relatorioPDF;

