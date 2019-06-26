
$(document).ready(function () {
    $("label").bind("click", function () {
        if(document.getElementById("menu").style.display == 'none'){
        document.getElementById("menu").style.display = 'flex';
        }
        else{
            document.getElementById("menu").style.display = 'none'; 
        }
    });
});

var i = 0;
var k = 0;
var qtdPratos = 9;

function mostrarMais() {

    var vetImgPrato=["img/prato7.jpg","img/prato8.jpg","img/prato9.jpg","img/prato10.jpg","img/prato11.jpg",
                     "img/prato12.jpg","img/prato13.jpg","img/prato14.jpg","img/prato15.jpg","img/prato16.jpg",
                     "img/prato17.jpg","img/prato18.jpg","img/prato19.jpg","img/prato20.jpg","img/prato21.jpg",
                     "img/prato22.jpg","img/prato23.jpg","img/prato24.jpg","img/prato25.jpg","img/prato26.jpeg",
                     "img/prato27.jpg","img/prato28.jpg","img/prato29.jpg","img/prato30.jpeg"];

    var vetNomePrato=["Salsicha Alemã","Setas a La plancha con Jamón","Fabada Asturiana","Mejillón Gallego",
                      "Polvorón","Escondidinho de Carne de Sol","Tapioca","Bobó de Camarão","Bolo de Rolo",
                      "Torresmo","Banana assada","Ensopado com quiabo","Filé de frango","Macaxeira","Feijoada",
                      "Queijo do Serro","Feijão tropeiro","Joelho de Porco","Acarajé","Costela de Porco",
                      "Caldo Verde","Sericaia com ameixas de Elvas","Francesinha Especial","Posta à Mirandesa"];

    var vetPrecoPrato=["R$ 7.50","R$ 42.30","R$ 12.60","R$ 12.60","R$ 14.20","R$ 11.80","R$ 20.60","R$ 17.30",
                      "R$ 14.60","R$ 13.50","R$ 15.40","R$ 14.20","R$ 11.80","R$ 20.60","R$ 17.30","R$ 14.20",
                      "R$ 11.80","R$ 20.60","R$ 42.30","R$ 12.60","R$ 12.60","R$ 14.20","R$ 11.80","R$ 20.60",];
    var textoItem;
    var span;
    var img;
    var h1;
    var h2;
    var temp;
    var idSpan;

    while(k < qtdPratos && vetNomePrato[i] != undefined){
        temp = i;
        idSpan = i;

        span = document.createElement("span");
        span.setAttribute("id", idSpan);
        document.querySelector("#conteiner").appendChild(span);

        img = document.createElement("IMG");
        img.setAttribute("src", vetImgPrato[temp]);
        document.getElementById(idSpan).appendChild(img);
    
        h2 = document.createElement("h2");
        textoItem = document.createTextNode(vetNomePrato[i]);
        h2.appendChild(textoItem);
        document.getElementById(idSpan).appendChild(h2);

        h1 = document.createElement("h1");
        textoItem = document.createTextNode(vetPrecoPrato[i])
        h1.appendChild(textoItem);
        document.getElementById(idSpan).appendChild(h1);

        $(document).ready(function () {
            $("button").bind("click", function () {
                if(vetPrecoPrato[i] == undefined){
                    document.getElementById("BtMostrarMais").style.display = 'none';
                }
            });
        });
        k++;
        i++;
    }
    k = 0;
}
var botao = document.querySelector("#BtMostrarMais");
botao.onclick = mostrarMais;
