package com.example.demo.Email;

import com.example.demo.PlanoCliente.PlanoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void enviarEmailDeNovoPlano(PlanoCliente planoCliente) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(planoCliente.getCliente().getEmail());
        mensagem.setSubject("Seu Novo Plano da Nexus foi Ativado!");

        String nomeCliente = planoCliente.getCliente().getNome();
        String nomePlano = planoCliente.getPlano().getNome();
        LocalDate dataInicio = planoCliente.getDataInicio();
        LocalDate dataFim = planoCliente.getDataFim();
        int duracaoPlano = planoCliente.getPlano().getDuracao();

        String dataInicioFormatada = dataInicio.format(dateFormatter);
        String dataFimFormatada = dataFim.format(dateFormatter);

        mensagem.setText(String.format("Olá, %s!\n\nSeu novo plano '%s' foi ativado com sucesso!\n\nDetalhes do seu plano:\n- Início: %s\n- Término: %s\n- Duração: %d dias\n\nAproveite ao máximo sua jornada conosco!\n\nPara mais informações sobre seu plano e nossos serviços, visite nosso site ou entre em contato conosco.\n\nAtenciosamente,\nEquipe Nexus",
                nomeCliente, nomePlano, dataInicioFormatada, dataFimFormatada, duracaoPlano));

        mailSender.send(mensagem);
    }


}