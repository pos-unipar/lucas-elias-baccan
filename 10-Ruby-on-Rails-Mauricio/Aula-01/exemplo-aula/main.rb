name = 'Lucas Elias Baccan'

puts 'Meu nome é: ' + name

puts "Meu nome é: #{name}"

puts "----------------------"

1..10.times do |num|
 puts num
end

puts "----------------------"

max_number = 1
while max_number <= 10
  if max_number % 2 == 0
    puts "#{max_number} é um número par"
  end
  max_number = max_number + 1
end

puts "----------------------"

max_number = 1
while max_number <= 10
  if max_number.even?
    puts "#{max_number} é um número par"
  end
  max_number = max_number + 1
end

puts "----------------------"

numeros = [1,2,3,4,5,6,7,8,9,10]
numeros.each do |num|
  puts num if num.even? 
end

puts "----------------------"

frase = 'lucas elias baccan'

puts frase.capitalize
puts frase.upcase
puts frase.downcase

list_of_words = frase.split(' ')
list_of_words.each do |word|
    word.capitalize!
end
frase_completa = list_of_words.join(' ')
puts frase_completa

puts "----------------------"

frase = 'lucas elias baccan'

puts frase.split(' ').map(&:capitalize).join(' ')

puts "----------------------"

frase = 'lucas elias baccan'

puts frase.split(' ').map {|parte| parte.capitalize }.join(' ')

puts "----------------------"

class Persson
    def printar_nome(nome="Bastiao")
        puts nome
    end

    def self.printar_random
        puts rand(100)
    end
end

p1 = Persson.new
p1.printar_nome

p2 = Persson.new
p2.printar_nome("Lucas")

Persson.printar_random

puts "----------------------"
