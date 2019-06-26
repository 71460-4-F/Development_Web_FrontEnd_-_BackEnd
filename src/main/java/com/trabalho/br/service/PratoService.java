package com.trabalho.br.service;

import com.trabalho.br.model.Prato;
import com.trabalho.br.repository.PratoRepository;
import com.trabalho.br.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PratoService {

    @Autowired
    private PratoRepository pratoRepository;

    public void save(Prato prato, @RequestParam(value = "imagem") MultipartFile imagem) {
        prato.setStatus(1);
        pratoRepository.save(prato);
        String caminho = "images/" + prato.getNome() + prato.getIdPrato() + ".png";
        ImageUtil.saveImage(caminho, imagem);
    }

    public List<Prato> listPratos() {
        return pratoRepository.findAll();
    }

    public void delete(Long id) {
        String caminho = "images/" + serchById(id).getNome() + serchById(id).getIdPrato() + ".png";
        ImageUtil.deleteImage(caminho);

        Prato prato = pratoRepository.getOne(id);
        prato.setStatus(0);
        pratoRepository.save(prato);
    }

    public Prato serchById(Long id) {
        return pratoRepository.getOne(id);
    }
}
