import itertools
import re
import sys

def main():
  output = []
  cases = int(sys.stdin.readline())
  for _ in itertools.repeat(None, cases):
    matrix = []
    order = int(sys.stdin.readline())
    for _ in itertools.repeat(None, order):
      row = []
      polynomial_strings = sys.stdin.readline().split()
      for polystr in polynomial_strings:
        polynomial = PolynomialStringParser(polystr).build()
        row.append(polynomial)
      matrix.append(row)
    output.append(even_coeff(matrix, order))
  print "\n".join(output)

def even_coeff(matrix, order):
  determinant = det(matrix, order)
  determinant.simplify()
  for coeff in determinant.coeffs:
    if coeff % 2 == 1:
      return "Some Odd"
  return "All Even"
 
def det(matrix, order):
  determinant = Polynomial([], [])
  if order == 1:
    return matrix[0][0]
  else:
    for j in xrange(order):
      p1 = matrix[0][j]
      cofactor_matrix = cofactor(matrix, 0, j, order)
      p2 = det(cofactor_matrix, order - 1)
      term = p1.polymul(p2)
      if (j & 1):
        term.polyneg()
      determinant = determinant.polyadd(term)
    return determinant

def cofactor(matrix, i, j, order):
  cofactor_matrix = []
  for row in xrange(order):
    cofactor_row = []
    for col in xrange(order):
      if row != i and col != j:
        cofactor_row.append(matrix[row][col])
    if row != i:
      cofactor_matrix.append(cofactor_row)
  return cofactor_matrix

class Polynomial:
  def __init__(self, coeffs, variables):
    self.coeffs = coeffs
    self.variables = variables
    self.num_terms = len(self.coeffs)

  def simplify(self):
    for i in xrange(self.num_terms):
      variable = self.variables[i]
      for k in xrange(self.num_terms):
        if i != k and variable == self.variables[k]:
          self.coeffs[i] += self.coeffs[k]
          self.coeffs[k] = None
          self.variables[k] = None
    self.coeffs = [c for c in self.coeffs if c != None]
    self.variables = [v for v in self.variables if v != None]
    self.num_terms = len(self.coeffs)

  def polyneg(self):
    self.coeffs = [-c for c in self.coeffs]

  def polymul(self, other):
    self.simplify()
    other.simplify()
    coeffs = []
    variables = []
    for i in xrange(self.num_terms):
      for k in xrange(other.num_terms):
        coeffs.append(self.coeffs[i] * other.coeffs[k])
        new_variable = other.variables[k].copy()
        for (key, val) in self.variables[i].items():
          if key in other.variables[k].keys():
            new_variable[key] += 1
          else:
            new_variable[key] = 1
        variables.append(new_variable)
    return Polynomial(coeffs, variables)

  def polyadd(self, other):
    self.simplify()
    other.simplify()
    coeffs = []
    variables = []
    for i in xrange(self.num_terms):
      variable = self.variables[i]
      coeff = self.coeffs[i]
      if variable in other.variables:
        other_coeff = other.coeffs[other.variables.index(variable)]
        coeffs.append(coeff + other_coeff)
      else:
        coeffs.append(coeff)
      variables.append(variable)
    for k in xrange(other.num_terms):
      other_variable = other.variables[k]
      other_coeff = other.coeffs[k]
      if other_variable not in self.variables:
        coeffs.append(other_coeff)
        variables.append(other_variable)
    return Polynomial(coeffs, variables)

  def __str__(self):
    result = ""
    for i in xrange(self.num_terms):
      coeff = self.coeffs[i]
      if coeff != 0:
        if coeff > 0:
          result += '+'
        result += str(coeff)
        for (key, val) in self.variables[i].items():
          result += (key * val)
    return result

class PolynomialStringParser:
  def __init__(self, polystr):
    self.polystr = polystr

  def build(self):
    self.polystr = self.parse()
    coeffs = []
    variables = []
    pattern = re.compile(r'(\+|\-)([0-9]+)([A-Za-z]*)')
    for (sign, coeff, var) in re.findall(pattern, self.polystr):
      if sign == '-':
        coeffs.append(-1 * int(coeff))
      else:
        coeffs.append(int(coeff))
      var_dict = {}
      for c in var:
        if c in var_dict:
          var_dict[c] += 1
        else:
          var_dict[c] = 1
      variables.append(var_dict)
    return Polynomial(coeffs, variables)

  def parse(self):
    if (not self.polystr.startswith('-')) and (not self.polystr.startswith('+')):
      self.polystr = "".join(("+", self.polystr))
    num_terms = self.polystr.count("+") + self.polystr.count("-")
    pattern = re.compile(r'(\+|\-)([A-Za-z]+)')
    insert_default_coeff = lambda nocoeff: "".join(
        (nocoeff.group(0)[:1], '1', nocoeff.group(0)[1:]))
    return re.sub(pattern, insert_default_coeff, self.polystr, count=num_terms)

if __name__ == "__main__":
  main()
