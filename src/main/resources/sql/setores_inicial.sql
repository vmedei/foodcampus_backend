-- Script para popular a tabela de setores com dados iniciais da UFRN
-- Execute este script após criar as tabelas

INSERT INTO setores (nome, sigla, latitude, longitude, descricao, endereco, ativo, data_criacao) VALUES
('Centro de Tecnologia', 'CT', -5.837000, -35.204100, 'Centro de cursos de engenharia e tecnologia', 'Campus Central UFRN', true, NOW()),
('Centro de Ciências Sociais Aplicadas', 'CCSA', -5.838000, -35.202500, 'Centro de administração, direito e serviço social', 'Campus Central UFRN', true, NOW()),
('Centro de Biociências', 'CB', -5.835500, -35.205500, 'Centro de ciências biológicas e da saúde', 'Campus Central UFRN', true, NOW()),
('Instituto Metrópole Digital', 'IMD', -5.834000, -35.207000, 'Instituto de tecnologia da informação', 'Campus Central UFRN', true, NOW()),
('Praça de Alimentação Central', 'PAC', -5.836500, -35.203500, 'Área central de alimentação do campus', 'Campus Central UFRN', true, NOW()),
('Centro de Ciências Exatas e da Terra', 'CCET', -5.836000, -35.204800, 'Centro de matemática, física e geologia', 'Campus Central UFRN', true, NOW()),
('Centro de Ciências Humanas, Letras e Artes', 'CCHLA', -5.839000, -35.201500, 'Centro de humanas, letras e artes', 'Campus Central UFRN', true, NOW()),
('Escola de Saúde', 'ESUFRN', -5.834500, -35.203000, 'Escola de Saúde da UFRN', 'Campus Central UFRN', true, NOW());

-- Verificar se os dados foram inseridos corretamente
SELECT * FROM setores ORDER BY sigla; 