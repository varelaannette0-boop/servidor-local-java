package com.labanta.servidorlocal.ServicoService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmailBoasVindas(String emailDestino, String nomeUtilizador) {

        // Criar um email simples (texto limpo)
        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setTo(emailDestino);
        mensagem.setSubject("Bem-vindo ao Marketplace!");
        mensagem.setText("Olá " + nomeUtilizador + "!\n\n" +
                "A tua conta foi criada com sucesso. Já podes fazer login " +
                "e explorar os nossos serviços. \n\n" +
                "Com os melhores cumprimentos, \nEquipa do Marketplace") ;

        // Enviar!
        mailSender.send(mensagem);

    }
    public void enviarEmailOrcamento(String emailDestino, String nomeServico, Double precoConvertido, String moeda) {

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(emailDestino);
        mensagem.setSubject("O teu Orçamento do Marketplace");

        // Criar o texto do corpo do email
        String texto = String.format(
                "Olá!\n\nAqui tens o orçamento solicitado para o serviço:\n\n" +
                        "Serviço: %s\n" +
                        "Preço Final: %.2f %s\n\n" +
                        "Este valor foi calculado com a taxa de câmbio em tempo real.\n" +
                        "Obrigado por usares o nosso Marketplace!",
                nomeServico, precoConvertido, moeda
        );

        mensagem.setText(texto);
        mailSender.send(mensagem);
    }


}
