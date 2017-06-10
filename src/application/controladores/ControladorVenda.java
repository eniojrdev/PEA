package application.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.GerenciadorCenas;
import application.Janelas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

public class ControladorVenda extends Controlador implements Initializable 
{
	private List<ItemCarrinho> produtosCarrinho;
	private double total;
	
	@FXML private ComboBox<String> formaPagamento;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		this.produtosCarrinho = Controlador.carrinho.getProdutos();
		total = ControladorCarrinho.carrinho.calcularValorTotal();
		ObservableList<String> items = FXCollections.observableArrayList();

		items.addAll("Boleto Bancário... R$" + Double.toString(total),
					"Cartão de Crédito... 1 x R$" + Double.toString(total),
					"Cartão de Crédito... 2 x R$" + Double.toString(Math.floor(total/2 * 100) / 100),
					"Paypal... R$" + Double.toString(total));
		formaPagamento.setItems(items);
	}
	
	@FXML
	private void concluir()
	{
		try
		{
			// Verificar campos
			// Adicionar a venda na lista
			// Resetar o carrinho e ir pra tela principal
			carrinho.esvaziar();
			GerenciadorCenas.limparHistorico();
			GerenciadorCenas.irPara(PRINCIPAL);
			Janelas.mensagem("Sucesso", "Compra realizada com sucesso.", AlertType.CONFIRMATION);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@FXML
	private void cancelar() throws IOException
	{
		GerenciadorCenas.irPara(PRINCIPAL);
	}
}
