//maskaras
$("#inputCEP").mask("99999-999");
$("#inputCPF").mask("999.999.999-99");
$('#inputDATA').mask("99/99/9999");
$('.time').mask('00:00:00');
$('.date_time').mask('00/00/0000 00:00:00');
$('.cpf').mask('000.000.000-00', {
	reverse : true
});
$('#demo').mask('999.999.999.999.999,99', {
	reverse : true
});
$("#inputGRANA").maskMoney({
	prefix : 'R$ ',
	thousands : '.',
	decimal : ',',
	affixesStay : true
});

// somar total pedidos
$(function() {
	var total = 0.00;
	$('.valorItem').each(function() {
		total += eval($(this).text());
	});
	$('.valorTotal').html("Valor total: R$ " + total.toFixed(2));
});

// troca para virgula
$(function() {
	var total = 0.00;
	$('#valorTotalItem').each(function() {
		total += eval($(this).text());
	});
	$('#valorTotalItem').html(
			"Valor total do pedido: R$ " + total.toFixed(2).replace('.', ','));
});

// valor moeda br
// $(function(){
// var total = 0.00;
// $('.valorMoeda').each(function(){
// total += eval($(this).text());
// });
// var valor = +total.toFixed(2);
// var moeda = valor.toLocaleString("pt-BR", { style: "currency" ,
// currency:"BRL"});
// $('.valorMoeda').html(moeda);
// });

// trocar ponto por virgula no preço
function alteraPonto(valorInput) {
	// alert("Valor original: " + valorInput.val());
	// alert("Valor com virgula: " + valorInput.val().replace(".", ","));
	$(valorInput).val(valorInput.val().replace(".", ","));
}