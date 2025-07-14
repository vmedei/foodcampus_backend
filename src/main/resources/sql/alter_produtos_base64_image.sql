-- Script para alterar a coluna base_64_image da tabela produtos
-- Executar este script no banco de dados para corrigir o erro de tamanho

-- Alterar a coluna base_64_image para LONGTEXT para suportar imagens em base64
ALTER TABLE produtos 
MODIFY COLUMN base_64_image LONGTEXT NOT NULL;

-- Verificar a alteração
DESCRIBE produtos; 